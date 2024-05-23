import React, { useCallback, useEffect, useState } from "react";
import axios from "axios";
import RecipesModal from "../recipe/RecipesModal";
import "../../css/recipe.css";
import { useInView } from "react-intersection-observer";

const RecipesComponent = (props) => {
  const { 검색어, 카테고리ID } = props;
  const [recipes, setRecipes] = useState([]);
  const [ref, inView] = useInView();
  const [loading, setLoading] = useState(false);
  const [hasMore, setHasMore] = useState(true);
  const [page, setPage] = useState(0);

  const [selectedRecipe, setSelectedRecipe] = useState(null);
  const [showModal, setShowModal] = useState(false);

  const getRecipes = useCallback(async () => {
    setLoading(true);
    try {
      const response = await axios.get("/recipe/search", {
        params: {
          categoryId: 카테고리ID,
          name: 검색어,
          page: page,
        },
      });
      if (response.data.response.content.length > 0) {
        setRecipes((prevState) => [...prevState, ...response.data.response.content]);
        setPage((prevState) => prevState + 1);
      } else {
        setHasMore(false);
      }
    } catch (error) {
      console.error("레시피 가져오기 오류 : " + error);
    }
    setLoading(false);
  }, [page, 검색어, 카테고리ID]);

  useEffect(() => {
    if (inView && !loading && hasMore) {
      getRecipes();
    }
  }, [inView, loading, hasMore]);

  useEffect(() => {
    getRecipes();
  }, []);

  const handleRecipeClick = (recipeId) => {
    setSelectedRecipe(recipeId);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setSelectedRecipe(null);
    setShowModal(false);
  };

  return (
    <div className="recipes-grid">
      {recipes.map((item, index) => (
        <React.Fragment key={index}>
          <div key={item.id} className="recipe-item" ref={index === recipes.length - 1 ? ref : null}>
            <img src={item.recipeImage} alt={`Image ${item.id}`} onClick={() => handleRecipeClick(item)} />
            <div className="recipe-overlay">
              <div>{item.recipeName}</div>
              <br />
              <div>{item.writerName}</div>
            </div>
          </div>
        </React.Fragment>
      ))}
      {selectedRecipe !== null && <RecipesModal recipeId={selectedRecipe.recipeId} handleClose={handleCloseModal} show={showModal} />}
    </div>
  );
};

export default RecipesComponent;
