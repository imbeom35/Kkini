import React from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import axios from "axios";

const goWithdrawal = () => {
  axios
    .delete("/mypage/withdrawal")
    .then(() => {
      window.location.href = process.env.REACT_APP_BASE_URL + "/member/logout";
    })
    .catch((err) => {
      console.error(err);
    });
};

function Withdrawal() {
  const navigate = useNavigate();

  return (
    <div className="mx-auto">
      <h2>돌이킬 수 없는 선택이오.</h2>
      <h2>두 번 묻지 않을테요.</h2>
      <h2 style={{ color: "red" }}>회원탈퇴 하시겠소?</h2>
      <div>
        <Button variant="danger" onClick={goWithdrawal} style={{ marginTop: "100px" }}>
          확인
        </Button>
      </div>
      <div>
        <Button onClick={() => navigate(-1)}>뒤로가기</Button>
      </div>
    </div>
  );
}

export default Withdrawal;
