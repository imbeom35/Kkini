import React, { useState } from "react";
import { Navbar, Container, Nav } from "react-bootstrap";
import RecommendedFeed from "../../components/search/RecommendedFeed";
import FeedComponent from "../../components/search/FeedComponent";
import AccountComponent from "../../components/search/AccountComponent";

const N2Search = () => {
  let [탭, 탭변경] = useState(0);
  let [검색어, 검색어변경] = useState("");

  // 검색어 초기화 함수
  const clearSearch = () => {
    검색어변경("");
  };

  return (
    <div style={{ marginTop: "20px" }}>
      <div>
        <input type="text" placeholder="검색어를 입력하세요" value={검색어} onChange={(e) => 검색어변경(e.target.value)} />
        {검색어.trim() !== "" && <button onClick={clearSearch}>x</button>}
      </div>
      {검색어.trim() !== "" && (
        <div>
          <Navbar className="justify-content-center">
            <Container>
              <Nav className="mx-auto" defaultActiveKey="link-0">
                <Nav.Link
                  onClick={() => {
                    탭변경(0);
                  }}
                  eventKey="link-0"
                >
                  피드
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    탭변경(1);
                  }}
                  eventKey="link-1"
                >
                  계정
                </Nav.Link>
              </Nav>
            </Container>
          </Navbar>
        </div>
      )}
      <div>
        {검색어.trim() === "" ? <RecommendedFeed /> : null}
        {검색어.trim() !== "" && 탭 === 0 ? <FeedComponent 검색어={검색어} /> : null}
        {검색어.trim() !== "" && 탭 === 1 ? <AccountComponent 검색어={검색어} /> : null}
      </div>
    </div>
  );
};

export default N2Search;
