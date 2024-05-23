// Redirect.js
import React, { useEffect, useState } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import { changeToken } from "../../store";
import { useDispatch, useSelector } from "react-redux";
import setAuthorizationToken from "../../apis/utils/setAuthorizationToken.js";

const Redirect = ({ setIsLogIn }) => {
  let state = useSelector((state) => state);
  let dispatch = useDispatch();
  const navigate = useNavigate();

  const [searchParams] = useSearchParams();
  const token = searchParams.get("token");

  useEffect(() => {
    if (token) {
      setIsLogIn(true);

      // 토큰 값을 객체로 감싸서 디스패치
      dispatch(changeToken({ token }));

      localStorage.setItem("jwtToken", token);
      setAuthorizationToken(token);
      navigate("/home/feed");
    } else {
      navigate("/login");
    }
  }, [token]);

  return (
    <div>
      <h2>인증 코드 받는 중</h2>
    </div>
  );
};

export default Redirect;
