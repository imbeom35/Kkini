import React, { useCallback, useEffect, useState } from "react";
import axios from "axios";
import "../../css/recipe.css";
import FeedModal from "./../feed/FeedModal";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import ThumbDownOffAltRoundedIcon from "@mui/icons-material/ThumbDownOffAltRounded";
import Loading from "../../routes/pages/Loading";
import { useInView } from "react-intersection-observer";

const RecommendedFeed = () => {
  const [data, setData] = useState([]);
  const [selectedPost, setSelectedPost] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [loading, setLoading] = useState(true);
  const [recipeLoading, setRecipeLoading] = useState(false);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(true);
  const [ref, inView] = useInView();

  const getAlgorithm = useCallback(async () => {
    if (!hasMore || recipeLoading) {
      return;
    }
    setRecipeLoading(true);
    try {
      const response = await axios.get("/post/algorithm", {
        params: {
          page: page,
        },
      });
      if (response.data.response.content.length > 0) {
        setData((prevState) => [...prevState, ...response.data.response.content]);
        setPage((prevState) => prevState + 1);
        setLoading(false);
      } else {
        setHasMore(false);
      }
    } catch (error) {
      console.error("추천 알고리즘 가져오기 오류 : " + error);
    }
    setRecipeLoading(false);
  }, [page]);

  const handlePostClick = (id) => {
    setSelectedPost(id);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setSelectedPost(null);
    setShowModal(false);
  };

  useEffect(() => {
    if (inView && !recipeLoading && hasMore) {
      getAlgorithm();
    }
  }, [inView, recipeLoading, hasMore]);

  useEffect(() => {
    getAlgorithm();
  }, []);

  if (loading) {
    return <Loading></Loading>;
  }

  return (
    <div className="recipes-grid">
      {data.map((item, index) => (
        <React.Fragment key={index}>
          <div key={item.id} className="recipe-item" ref={index === data.length - 1 ? ref : null}>
            <img src={item.imageList[0]} alt={`Image ${item.id}`} onClick={() => handlePostClick(item)} />
            <div className="recipe-overlay">
              <div>
                <FavoriteBorderIcon /> {item.likeCnt}
              </div>
              <div>
                <ThumbDownOffAltRoundedIcon /> {item.disLikeCnt}
              </div>
              <div>{item.memberName}</div>
            </div>
          </div>
        </React.Fragment>
      ))}
      {selectedPost !== null && <FeedModal selectedPost={selectedPost} handleClose={handleCloseModal} show={showModal} />}
    </div>
  );
};

export default RecommendedFeed;
