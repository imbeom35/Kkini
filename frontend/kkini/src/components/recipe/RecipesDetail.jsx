import React, { useEffect, useState } from "react";
import axios from "axios";
import AccessTimeIcon from "@mui/icons-material/AccessTime";
import LocalDiningIcon from "@mui/icons-material/LocalDining";
import CategoryIcon from "@mui/icons-material/Category";
import { ListGroup, ListGroupItem } from "react-bootstrap";
import EditNoteIcon from "@mui/icons-material/EditNote";
import RiceBowlIcon from "@mui/icons-material/RiceBowl";

function RecipesDetail(props) {
  const recipeId = props.recipeId;
  const [recipeData, setRecipeData] = useState(null);
  const [tmp, setTmp] = useState("");

  const divide = (steps) => {
    setTmp(steps.split("\n"));
  };

  useEffect(() => {
    axios
      .get(`/recipe/${recipeId}`)
      .then((response) => {
        setRecipeData(response.data.response);
        divide(response.data.response.steps);
      })
      .catch((error) => {
        console.error("Error fetching recipe details:", error);
      });
  }, [recipeId]);

  return (
    <div>
      {recipeData && (
        <div>
          <h3 className="text-center">
            <LocalDiningIcon />
            {recipeData.name}
          </h3>
          <img className="mx-auto p-2" style={{ maxHeight: "300px", borderRadius: "10px" }} src={recipeData.image} alt={`Image ${recipeData.recipeId}`} />
          <ListGroup style={{ listStyle: "none" }}>
            <ListGroupItem>
              <CategoryIcon style={{ marginRight: "10px" }}></CategoryIcon>
              {recipeData.categoryName}
            </ListGroupItem>
            <ListGroupItem>
              <AccessTimeIcon style={{ marginRight: "10px" }}></AccessTimeIcon>
              {recipeData.time} 분
            </ListGroupItem>
            <ListGroupItem>
              <RiceBowlIcon style={{ marginRight: "10px" }}></RiceBowlIcon>
              {recipeData.ingredient}
            </ListGroupItem>
            <ListGroupItem>
              <EditNoteIcon style={{ marginRight: "10px" }} />
              {tmp.map((res) => (
                <p key={res}>{res}</p>
              ))}
            </ListGroupItem>
          </ListGroup>
        </div>
      )}
    </div>
  );
}

export default RecipesDetail;
