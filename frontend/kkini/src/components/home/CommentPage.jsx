import React, { useState, useEffect } from "react";
import { Avatar } from "@mui/material";
import axios from "axios";
import { useLocation } from "react-router-dom";
import "../../css/comment.css";

function CommentsPage({ comments, onCommentsChange, postId }) {
  const [comment, setComment] = useState(comments);
  const [replyToIndex, setReplyToIndex] = useState(null);
  const [editIndex, setEditIndex] = useState(null);
  const [submitTrigger, setSubmitTrigger] = useState(false);
  const location = useLocation();

  const effectivePostId = postId || location.state?.postId;

  useEffect(() => {
    if (!effectivePostId) {
      console.error("postId is not defined.");
      return;
    }

    if (submitTrigger) {
      const submitComment = async () => {
        let data = {
          postId: effectivePostId,
          contents: comment,
        };

        let endpoint;
        let method;

        if (editIndex !== null) {
          endpoint = `/comment/update/${editIndex}`;
          method = "PUT";
        } else {
          endpoint = `/comment/`;
          method = "POST";
          if (replyToIndex !== null) {
            data.parentsId = replyToIndex;
          }
        }

        try {
          const response = await axios({ method, url: endpoint, data });
          if (response.data.success) {
            onCommentsChange(); // 댓글 작성, 수정, 삭제 후 댓글 목록 다시 가져오기
          }
        } catch (error) {
          console.error("Error posting comment:", error.response ? error.response.data : error.message);
        }
      };

      submitComment();
      setComment("");
      setEditIndex(null);
      setReplyToIndex(null);
      setSubmitTrigger(false);
    }
  }, [submitTrigger, effectivePostId, comment, onCommentsChange, editIndex, replyToIndex]);

  const handleCommentSubmit = (e) => {
    e.preventDefault();

    if (!effectivePostId) {
      return;
    }

    setSubmitTrigger(true);
  };

  const handleCommentChange = (e) => setComment(e.target.value);

  const handleDeleteClick = async (commentIndex) => {
    try {
      const response = await axios.delete(`/comment/${commentIndex}`);
      if (response.data.success) {
        onCommentsChange(); // 댓글 삭제 후 댓글 목록 다시 가져오기
      }
    } catch (error) {
      console.error("Error deleting comment:", error.response ? error.response.data : error.message);
    }
  };

  return (
    <div className="CommentsContainer">
      {/* 댓글 목록 */}
      <div className="CommentsList">
        {comments &&
          comments.map((item, index) => (
            <div className="Comment" key={index}>
              {/* 사진 */}
              <Avatar className="my-auto ml-3" />

              {/* 댓글 */}
              <div className="CommentContent my-auto ml-3">
                {/* 아이디 */}
                <p style={{ fontWeight: "bold" }}>{item.parents.nickname}</p>

                {/* 내용 */}
                <p>{item.parents.contents}</p>

                {/* 삭제 */}
                <button style={{ fontSize: "10px", marginBottom: '10px' }} onClick={() => handleDeleteClick(item.parents.id)}>
                  삭제
                </button>
              </div>
            </div>
          ))}
      </div>

      {/* 댓글 입력 */}
      <form className="CommentForm" onSubmit={handleCommentSubmit}>
        <input className="CommentInput" type="text" placeholder="댓글을 입력하세요..." value={comment} onChange={handleCommentChange} required />
        <button className="CommentButton" type="submit">
          작성
        </button>
      </form>
    </div>
  );
}

export default CommentsPage;
