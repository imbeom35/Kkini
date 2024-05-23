import "./App.css";
import React, { useEffect, useState } from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Home from "./routes/pages/Home.jsx";
import Redirect from "./routes/login/Redirect.jsx";
import N1Home from "./routes/navi/N1_home.jsx";
import N2Search from "./routes/navi/N2_search.jsx";
import N3Upload from "./routes/navi/N3_upload.jsx";
import N4Recipe from "./routes/navi/N4_recipe.jsx";
import N5Mypage from "./routes/navi/N5_mypage.jsx";
import "tailwindcss/tailwind.css";
import Oauth from "./routes/login/Oauth";
import Withdrawal from "./routes/login/Withdrawal";
import NotFound from "./components/home/NotFound";

import Book from "./routes/pages/Book";

import Paper from "@mui/material/Paper";
// App.js
function App() {
  const [isLogIn, setIsLogIn] = useState(false);

  function setScreenSize() {
    let vh = window.innerHeight * 0.01;
    document.documentElement.style.setProperty("--vh", `${vh}px`);
  }
  useEffect(() => {
    setScreenSize();
  });

  return (
    <div className="App">
      <Routes>
        <Route path="/" element={isLogIn ? <Home /> : <Navigate to="/login" />} />
        <Route path="/redirect" element={<Redirect setIsLogIn={setIsLogIn} />} />
        <Route path="/login" element={<Oauth />} />
        <Route path="/withdrawal" element={<Withdrawal />} />
        <Route path="/book" element={<Book />} />
        <Route path="/home/*" element={<Home />}>
          <Route path="feed" element={<N1Home />} />
          <Route path="search" element={<N2Search />} />
          <Route path="make" element={<N3Upload />} />
          <Route path="recipe" element={<N4Recipe />} />
          <Route path="info" element={<N5Mypage />} />
          <Route path="info/:userId" element={<N5Mypage />} />
          <Route path="*" element={<NotFound />} />
        </Route>
        <Route path="/error" element={<NotFound />}></Route>
        <Route path="/*" element={<NotFound />} />
      </Routes>
    </div>
  );
}

export default App;
