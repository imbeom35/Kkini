// Naver.js
import React, { useEffect, useState } from "react";
import styled from "styled-components";
import Paper from "@mui/material/Paper";
import Loading from "../pages/Intro";
import "../../css/intro.css";

const Oauth = () => {
  // window.scrollTo(0, 0);
  const [ready, setReady] = useState(true);

  useEffect(() => {
    setTimeout(() => {
      setReady(false);
    }, 1500);
  }, []); // 빈 dependency 배열을 추가하여 마운트될 때만 useEffect가 실행되도록 합니다.

  const NaverLogin = () => {
    window.location.href = process.env.REACT_APP_NAVER_LOGIN_URL;
  };

  if (ready) {
    return <Loading />;
  }

  return (
    <div style={{
          backgroundImage: 'url(/img/밥상.PNG)',
          backgroundSize: 'cover',
          backgroundRepeat: 'no-repeat',
          backgroundPosition: 'center'
      }}>
      <div>
        <Logo src="img/logo2.png" alt="로고" className="pulse mx-auto" />
        <Title style={{ margin: "0 auto", color: "white" }}>끼니에 어서오세요!</Title>
      </div>
      <Paper elevation={3} style={{ display: "contents" }}>
        <NaverLoginBtn2 onClick={NaverLogin} className="mx-auto">
          <NaverIcon alt="navericon" />
          <NaverBtnTitle>네이버 로그인</NaverBtnTitle>
        </NaverLoginBtn2>
      </Paper>
    </div>
  );
};

export default Oauth;

const NaverLoginBtn2 = styled.button`
  display: flex;
  align-items: center;
  width: 323px;
  height: 75px;
  background-color: #03c75a;
  border-radius: 6px;
  margin-top: 100px;
  /* border: 1px solid slategray; */
`;

// 로그인 버튼 사용가이드 링크를 들어가면 이미지를 받아 이렇게 적용이 가능하다 !
const NaverIcon = styled.div`
  width: 60px;
  height: 60px;
  margin-left: 10px;
  background: url("img/btnw.png") no-repeat center;
  background-size: 30px;
`;

const Title = styled.span`
  margin-left: 90px;
  color: slategray;
  font-weight: 900;
  font-size: 25px;
  line-height: 24px;
`;

const NaverBtnTitle = styled.span`
  margin: 0 auto;
  color: white;
  font-weight: 800;
  font-size: 25px;
  line-height: 24px;
`;

const Logo = styled.img`
  margin-top: 150px;
  margin-bottom: 50px;
  width: 250px;
  height: 250px;
`;
