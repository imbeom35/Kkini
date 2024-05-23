import React, { useEffect } from "react";
import { Routes, Route, useNavigate, useLocation } from "react-router-dom";
import NavigationBar from "../../components/home/NavigationBar.jsx";
import N1Home from "../navi/N1_home.jsx";
import N2Search from "../navi/N2_search.jsx";
import N3Upload from "../navi/N3_upload.jsx";
import N4Recipe from "../navi/N4_recipe.jsx";
import N5Mypage from "../navi/N5_mypage.jsx";
import CommentsPage from "../../components/home/CommentPage.jsx";
import Book from "./Book.jsx";

function Home({ onLogout }) {
  //persisted state
  const handleLogout = () => {
    onLogout();
  };

  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    if (!location.pathname.includes("/home")) {
      navigate("/home/feed");
    }
  }, [location, navigate]);

  return (
    <div style={{ margin: "0 auto", overflow: "visible" }}>
      <Routes>
        <Route path="feed" element={<N1Home />} />
        <Route path="search" element={<N2Search />} />
        <Route path="make" element={<N3Upload />} />
        <Route path="recipe" element={<N4Recipe />} />
        <Route path="info" element={<N5Mypage />} />
        <Route path="info/:userId" element={<N5Mypage />} />
        <Route path="comments" element={<CommentsPage />} />
        <Route path="book" element={<Book />} />
      </Routes>

      <NavigationBar></NavigationBar>
    </div>
  );
}

export default Home;
