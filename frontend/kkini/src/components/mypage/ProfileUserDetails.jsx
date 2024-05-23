import React, { useState, useEffect } from "react";
import { CiSettings } from "react-icons/ci";
import { useParams } from "react-router-dom";
import axios from "axios";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import styled from "styled-components";
import FollowModal from "./FollowModal";
import { useNavigate } from "react-router-dom";

export const ProfileUserDetails = ({ 내것 = 0, memid = "" }) => {
  const [data, setData] = useState("");
  const [follow, setFollow] = useState("");
  const [follower, setFollower] = useState("");
  const [isfollowing, setIsfollowing] = useState();
  const id = useParams();
  const selectedId = id.userId || "mypage";
  const navigate = useNavigate();

  useEffect(() => {
    // 마이페이지 정보 불러오기
    axios
      .get(`/mypage/info/${selectedId}`)
      .then((res) => {
        if (res.data.success) {
          setData(res.data.response);
        } else {
          navigate("/error");
        }
      })
      .catch((error) => {
        navigate("/error");
      });
    axios.get(`/follow/isFollow/${selectedId}`).then((res) => {
      if (res.data.response === 0) {
        setIsfollowing(false);
      } else {
        setIsfollowing(true);
      }
    });
    // 팔로우 수
    axios
      .get(`/follow/countFollow/${selectedId}`)
      .then((res) => {
        setFollow(res.data.response);
      })
      .catch((error) => {});
    // 팔로워 수
    axios
      .get(`/follow/countFollower/${selectedId}`)
      .then((res) => {
        setFollower(res.data.response);
      })
      .catch((error) => {});
  }, [selectedId, isfollowing]);

  //  팔로우 신청
  const handleFollow = () => {
    axios
      .post(`/follow/${selectedId}`)
      .then((res) => {
        if (res.data.success) {
          setIsfollowing(true);
        } else if (res.data.error.status === 400) {
          alert("본인은 팔로우 할 수 없어요");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  // 팔로우 취소
  const handleUnfollow = () => {
    axios
      .delete(`/follow/${selectedId}`)
      .then((res) => {
        if (res.data.success) {
          setIsfollowing(false);
        }
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const [show, setShow] = useState(false);

  const handleShow = () => setShow(true);
  const handleClose = () => {
    setShow(false);
  };

  const goLogout = () => {
    window.location.href = process.env.REACT_APP_LOGOUT_URL;
  };

  const goOut = () => {
    navigate("/withdrawal");
  };

  return (
    <div>
      <div className="py-10 w-full">
        <div className="flex items-center">
          <div className="w-[30%]">
            <div className="w-32 h-32 rounded-full flex items-center justify-center overflow-hidden mx-auto">
              <img className="w-full h-full object-cover" src={data.image} alt="프로필 이미지" />
            </div>
          </div>

          <div className="space-y-5 text-xs">
            <div className="flex space-x-10 items-center">
              <h4 className="my-auto pl-2">{data.nickname}</h4>
              {/* 팔로우 */}
              {내것 ? null : (
                <Button variant="primary" onClick={isfollowing ? handleUnfollow : handleFollow}>
                  {isfollowing ? "팔로우 취소" : "팔로우"}
                </Button>
              )}
              {내것 === 1 ? <CiSettings style={{ cursor: "pointer" }} size={20} onClick={handleShow}></CiSettings> : null}
            </div>
            <div className="flex space-x-10">
              <FollowModal whichOne="follow" />
              <p className="my-auto" style={{ fontSize: "17px" }}>
                {follow}
              </p>
              <FollowModal whichOne="follower"></FollowModal>
              <p className="my-auto" style={{ fontSize: "17px" }}>
                {follower}
              </p>
            </div>
          </div>
        </div>
      </div>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton style={{ textAlign: "center" }}>
          <Modal.Title>내 정보</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {data.authProvider === "NAVER" ? (
            <div style={{ display: "flex" }}>
              <NaverIcon />
              <h4 style={{ margin: 0, marginLeft: 4 }}>네이버 연동중</h4>
            </div>
          ) : (
            <p>연동이 되어 있지 않습니다</p>
          )}
          <Info className="mt-2">
            <h4>이름 : {data.name}</h4>
            <h4>닉네임 : {data.nickname}</h4>
            <h4>이메일 : {data.email}</h4>
          </Info>
        </Modal.Body>
        <CommentsContainer>
          <Button variant="info" onClick={goLogout}>
            로그아웃
          </Button>
          <Button className="mt-2" style={{ display: "block", textAlign: "right" }} variant="danger" onClick={goOut}>
            회원탈퇴
          </Button>
        </CommentsContainer>
        <Modal.Footer>
          <Button onClick={handleClose}>닫기</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

const CommentsContainer = styled.div`
  max-width: 550px;
  margin: 30px auto;
  padding: 10px;
`;

const NaverIcon = styled.div`
  width: 30px;
  height: 30px;
  background: url("/img/naverbtn2.png") no-repeat center;
  background-size: 30px;
`;

const Info = styled.div`
  margin-top: "30px";
`;
