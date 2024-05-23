import React, { useEffect, useState } from "react";
import axios from "axios";
import "../../css/recipe.css";
import FeedModal from "../feed/FeedModal";
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ThumbDownOffAltRoundedIcon from "@mui/icons-material/ThumbDownOffAltRounded";

function P4Scrap() {
  window.scrollTo(0, 0);
  const [scrapList, setScrapList] = useState([]);

  const [selectedPost, setSelectedPost] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const handlePostClick = (id) => {
    getPostDetail(id.postId);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setSelectedPost(null);
    setShowModal(false);
  };

  const getPostDetail = (postId) => {
    axios
    .get(`/post/detail/${postId}`)
    .then((res) => {
      setSelectedPost(res.data.response);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
  }

  useEffect(() => {
    axios
    .get("/scrap/list/mypage")
    .then((res) => {
      setScrapList(res.data.response.content);
    })
    .catch((error) => {
      console.error("Error fetching data:", error);
    });
  }, []);

  return (
    <div>
      {scrapList.length > 0 ? (
        <div className="recipes-grid">
          {scrapList.map((item) => (
            <div key={item.id} className="recipe-item">
              <img
                src={item.image}
                alt={`Image ${item.id}`}
                onClick={() => handlePostClick(item)}
              />
              <div className="recipe-overlay">
              <div><FavoriteBorderIcon /> {item.likeCnt}</div>
                <div><ThumbDownOffAltRoundedIcon /> {item.disLikeCnt}</div>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>등록된 스크랩이 없어요</p>
      )}
      {selectedPost !== null && (
        <FeedModal
          selectedPost={selectedPost}
          handleClose={handleCloseModal}
          show={showModal}
        />
      )}
    </div>
  );
}

export default P4Scrap;
