import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const AccountComponent = (props) => {
  const { 검색어 } = props;
  const [데이터, setData] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(`/member/search/${검색어}?page=0&size=10&sort=string`)
      .then((response) => {
        setData(response.data.response.content);
      })
      .catch((error) => {
        navigate("/error");
      });
  }, [검색어]);

  const goMypage = (memberId) => {
    navigate(`/home/info/${memberId}`);
  };

  return (
    <div>
      {데이터 ? (
        <div>
          {데이터.map((item) => (
            <div key={item.id}>
              <div className="mb-5" onClick={() => goMypage(item.memberId)} style={{ cursor: "pointer" }}>
                <img src={item.image} alt={`Image ${item.memberId}`} 
                  className="rounded-circle inline mr-5"
                />
                <span className="my-auto">{item.nickname}</span>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>검색 결과가 존재하지 않습니다.</p>
      )}
    </div>
  );
};

export default AccountComponent;
