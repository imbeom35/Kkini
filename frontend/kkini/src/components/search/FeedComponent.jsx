import React, { useState, useEffect } from "react";
import axios from "axios";
import "../../css/recipe.css";
import FeedModal from "../feed/FeedModal";
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ThumbDownOffAltRoundedIcon from "@mui/icons-material/ThumbDownOffAltRounded";

const FeedComponent = (props) => {
  const { 검색어 } = props;
  const [데이터, setData] = useState(null);

  const [selectedPost, setSelectedPost] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const handlePostClick = (id) => {
    setSelectedPost(id);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setSelectedPost(null);
    setShowModal(false);
  };

  useEffect(() => {
    axios
      .get("/post/search", {
        params: {
          search: 검색어,
          page: 0,
          size: 5,
        },
      })
      .then((response) => {
        setData(response.data.response.content);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, [검색어]);

  return (
    <div>
      {데이터 ? (
        <div className="recipes-grid">
          {데이터.map((item) => (
            <div key={item.id} className="recipe-item">
              <img src={item.imageList[0]} alt={`Image ${item.id}`} onClick={() => handlePostClick(item)} />
              <div className="recipe-overlay">
              <div><FavoriteBorderIcon /> {item.likeCnt}</div>
                <div><ThumbDownOffAltRoundedIcon /> {item.disLikeCnt}</div>
                <div>{item.memberName}</div>
              </div>
            </div>
          ))}
        </div>
      ) : null}
      {selectedPost !== null && <FeedModal selectedPost={selectedPost} handleClose={handleCloseModal} show={showModal} />}
    </div>
  );
};

export default FeedComponent;
