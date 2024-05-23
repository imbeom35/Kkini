import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import RecipesDetail from "./RecipesDetail";

function RecipesModal({ recipeId, handleClose, show }) {
  return (
    <Modal show={show} onHide={handleClose} animation={false}>
      <Modal.Body>
        <RecipesDetail recipeId={recipeId}></RecipesDetail>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          닫기
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default RecipesModal;
