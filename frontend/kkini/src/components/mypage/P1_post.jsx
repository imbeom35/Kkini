import React, { useEffect, useState } from "react";
import axios from "axios";
import "../../css/recipe.css";
import FeedModal from "../feed/FeedModal";
import { useParams } from "react-router";
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ThumbDownOffAltRoundedIcon from "@mui/icons-material/ThumbDownOffAltRounded";

function P1Post() {
  window.scrollTo(0, 0);
  const [postList, setPostList] = useState([]);

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
  const id = useParams();
  const selectedId = id.userId || "mypage";

  useEffect(() => {
    axios
      .get(`/post/${selectedId}`, {
        params: {
          page: 0,
        },
      })
      .then((res) => {
        setPostList(res.data.response.content);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  return (
    <div>
      {postList.length > 0 ? (
        <div className="recipes-grid">
          {postList.map((item) => (
            <div key={item.id} className="recipe-item">
              <img
                style={{ width: "100%" }}
                src={item.imageList[0]}
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
        <p>등록된 게시글이 없어요</p>
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

export default P1Post;
