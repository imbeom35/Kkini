import React, { useState, useEffect } from "react";
import axios from "axios";
import RecipesModal from "../recipe/RecipesModal";
import "../../css/recipe.css";
import { useParams } from "react-router";

function P2Recipe() {
  window.scrollTo(0, 0);
  const id = useParams();
  const selectedId = id.userId || "mypage";

  const [recipesList, setRecipesList] = useState([]);
  const [selectedRecipe, setSelectedRecipe] = useState(null);
  const [showModal, setShowModal] = useState(false);

  useEffect(() => {
    axios
      .get(`/recipe/mypage/${selectedId}`, {
        params: {
          page: 0,
        },
      })
      .then((response) => {
        setRecipesList(response.data.response.content);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
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
    <div>
      {recipesList.length > 0 ? (
        <div className="recipes-grid">
          {recipesList.map((item) => (
            <div key={item.recipeId} className="recipe-item">
              <img src={item.recipeImage} alt={`Image ${item.recipeId}`} onClick={() => handleRecipeClick(item)} />
              <div className="recipe-overlay">
                <div>{item.recipeName}</div>
                <br />
                <div>{item.writerName}</div>
              </div>
            </div>
          ))}
          {selectedRecipe !== null && <RecipesModal recipeId={selectedRecipe.recipeId} handleClose={handleCloseModal} show={showModal} />}
        </div>
      ) : (
        <p>등록된 레시피가 없어요</p>
      )}
    </div>
  );
}

export default P2Recipe;
