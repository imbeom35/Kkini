import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Post from "./../home/Post";

function FeedModal({ selectedPost, handleClose, show }) {
  return (
    <Modal show={show} onHide={handleClose} animation={false}>
      <Modal.Body>
        <Post
          avgPrice={selectedPost.avgPrice}
          commentCnt={selectedPost.commentCnt}
          contents={selectedPost.contents}
          createDateTime={selectedPost.createDateTime}
          disLikeCnt={selectedPost.disLikeCnt}
          postId={selectedPost.id}
          postImage={selectedPost.imageList}
          isScrap={selectedPost.isScrap}
          likeCnt={selectedPost.likeCnt}
          nickname={selectedPost.nickname}
          myPrice={selectedPost.myPrice}
          reaction={selectedPost.reaction}
          recipeId={selectedPost.recipeId}
          recipeName={selectedPost.recipeName}
        ></Post>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          닫기
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default FeedModal;
