import React, { useRef, useEffect, useState } from "react";
import axios from "axios";
import CommentsPage from "./CommentPage";
import "../../css/drawer.css";
import { width } from "dom7";

const Drawer = ({ isOpen, postId, onClose }) => {
  const ref = useRef();

  const [comments, setComments] = useState();

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (ref.current && !ref.current.contains(event.target)) {
        onClose();
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [onClose]);

  const fetchComments = async () => {
    if (!postId) return;
    try {
      const response = await axios.get(`/comment/${postId}`);
      if (response.data.success) {
        setComments(response.data.response); // 예상 응답 구조에 따라 수정 필요
      }
    } catch (error) {
      console.error("Error fetching comments:", error);
    }
  };

  useEffect(() => {
    fetchComments();
  }, []);

  return (
    <div
      className="drawer-container"
      style={{
        bottom: isOpen ? "0" : "-60%",
        width: '100%',
        maxWidth: '500px',
      }}
      isOpen={isOpen}
      ref={ref}
    >
      <div className="drawer-close" onClick={onClose}>
        <button>닫기</button>
      </div>
      <CommentsPage
        postId={postId}
        comments={comments}
        onCommentsChange={fetchComments} // 이 부분이 정확한지 확인
      />
    </div>
  );
};

export default Drawer;
