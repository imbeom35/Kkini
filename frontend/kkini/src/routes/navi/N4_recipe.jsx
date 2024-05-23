import React, { useState } from "react";
import { Navbar, Container, Nav } from "react-bootstrap";
import RecipesComponent from "../../components/search/RecipesComponent";
import "../../css/recipe.css";

const N4Recipe = () => {
  window.scrollTo(0, 0);
  const [카테고리ID, 카테고리ID변경] = useState(null);
  const [검색어, 검색어변경] = useState("");

  return (
    <div className="recipe" style={{ marginTop: "20px" }}>
      <div>
        <input type="text" placeholder="검색어를 입력하세요" value={검색어} onChange={(e) => 검색어변경(e.target.value)} />
        {검색어.trim() !== "" && <button onClick={() => 검색어변경("")}>x</button>}
      </div>
      {검색어.trim() !== "" && (
        <div>
          <Navbar className="justify-content-center">
            <Container>
              <Nav className="mx-auto" defaultActiveKey="link-0">
                <Nav.Link
                  onClick={() => {
                    카테고리ID변경(null);
                  }}
                  eventKey="link-0"
                >
                  전체
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    카테고리ID변경(1);
                  }}
                  eventKey="link-1"
                >
                  한식
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    카테고리ID변경(2);
                  }}
                  eventKey="link-2"
                >
                  중식
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    카테고리ID변경(3);
                  }}
                  eventKey="link-3"
                >
                  일식
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    카테고리ID변경(4);
                  }}
                  eventKey="link-4"
                >
                  양식
                </Nav.Link>
                <Nav.Link
                  onClick={() => {
                    카테고리ID변경(5);
                  }}
                  eventKey="link-5"
                >
                  기타
                </Nav.Link>
              </Nav>
            </Container>
          </Navbar>
        </div>
      )}
      <div>
        <RecipesComponent 검색어={검색어} 카테고리ID={카테고리ID}></RecipesComponent>
      </div>
    </div>
  );
};

export default N4Recipe;
