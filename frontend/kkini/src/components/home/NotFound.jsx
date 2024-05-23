import React from "react";
import { Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { useNavigate } from "react-router-dom";

const NotFound = () => {
  const navigate = useNavigate();
  const goBack = () => {
    navigate("/home/feed");
  };
  return (
    <div>
      <h1>404 NotFound</h1>
      <h3>해당 페이지는 존재하지 않습니다!!</h3>
      <h3>다른 URL로 접속해주세요~!</h3>
      <Button variant="primary" onClick={goBack}>
        돌아가기
      </Button>
      <p>계속 해당 페이지가 뜬다면 끼니 관리자에게 문의해주세요.</p>
    </div>
  );
};

export default NotFound;
