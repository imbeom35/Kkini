import React, { useEffect, useState } from "react";
import Modal from "@mui/material/Modal";
import Button from "@mui/material/Button";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import axios from "axios";
import { useParams } from "react-router-dom";
import { ImageListItem } from "@mui/material";
import { useNavigate } from "react-router-dom";

function FollowerModal({ open, onClose, whichOne, id, onHandler }) {
  const { userId } = useParams();
  const selectedUserId = userId || "mypage";
  const navigate = useNavigate();
  const [list, setList] = useState([]);

  const goMypage = (followerId) => {
    onClose();
    navigate(`/home/info/${followerId}`);
  };

  useEffect(() => {
    async function fetchFollowers() {
      try {
        const response = await axios.get(`/follow/${whichOne}List/${selectedUserId}`);
        setList(response.data.response.content);
      } catch (error) {
        navigate("/error");
      }
    }

    if (open) {
      fetchFollowers();
    }
  }, [open, selectedUserId]);

  return (
    <Modal open={open} onClose={onClose}>
      <div style={{ position: "absolute", top: "50%", left: "50%", transform: "translate(-50%, -50%)", backgroundColor: "white", padding: "16px" }}>
        <h2>{whichOne}s</h2>
        <List>
          {list.map((follower) => (
            <ListItem style={{ cursor: "pointer" }} key={follower.id} onClick={() => goMypage(follower.memberId)}>
              <img src={follower.image} />
              <ListItemText primary={follower.nickname} />
            </ListItem>
          ))}
        </List>
        <Button onClick={onClose}>Close</Button>
      </div>
    </Modal>
  );
}

function FollowModal({ whichOne }) {
  const [modalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  return (
    <div>
      <Button onClick={openModal}>{whichOne}</Button>
      <FollowerModal whichOne={whichOne} open={modalOpen} onClose={closeModal} />
    </div>
  );
}

export default FollowModal;
