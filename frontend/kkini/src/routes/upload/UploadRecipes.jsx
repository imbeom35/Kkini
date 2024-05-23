import React, { useEffect, useState } from "react";
import "../../css/Upload.css";
import HighlightOffIcon from "@mui/icons-material/HighlightOff";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function UploadRecipes() {
  const [selectedImage, setSelectedImage] = useState(null);
  const [previewUrl, setPreviewUrl] = useState(null);
  const navigate = useNavigate();

  const saveImage = (e) => {
    e.preventDefault();
    const file = e.target.files[0];
    if (file) {
      const previewURL = URL.createObjectURL(file);
      setSelectedImage(file);
      setPreviewUrl(previewURL);
    }
  };

  const deleteImage = () => {
    setSelectedImage(null);
    setPreviewUrl(null);
  };

  useEffect(() => {
    if (previewUrl) {
      return () => {
        URL.revokeObjectURL(previewUrl);
      };
    }
  }, [previewUrl]);

  const [selectedCategory, setSelectedCategory] = useState(null);

  const categoryOptions = [
    { id: 0, label: "선택" },
    { id: 1, label: "한식" },
    { id: 2, label: "중식" },
    { id: 3, label: "일식" },
    { id: 4, label: "양식" },
    { id: 5, label: "기타" },
  ];

  const [title, setTitle] = useState("");
  const [timeValue, setTimeValue] = useState("");
  const [materialValue, setMaterialValue] = useState("");
  const [contentValue, setContentValue] = useState("");

  const jsonData = {
    categoryId: selectedCategory,
    name: title,
    time: timeValue,
    ingredient: materialValue,
    steps: contentValue,
  };

  const handleFileUpload = () => {
    if (selectedImage) {
      const formData = new FormData();

      formData.append("file", selectedImage);

      formData.append(
        "data",
        new Blob([JSON.stringify(jsonData)], {
          type: "application/json",
        })
      );

      axios
        .post("/recipe", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          navigate("/home/recipe");
        })
        .catch((error) => {
          console.error("업로드 실패:", error);
        });
    } else {
      alert("이미지를 업로드하세요");
    }
  };

  return (
    <div className="uploader-wrapper">
      <div>
        <label style={{ marginRight: "20px" }}>이미지 선택</label>
        <input type="file" accept="image/*" onChange={saveImage} onClick={(e) => (e.target.value = null)} style={{ display: "none" }} />
        <button className="btnn" onClick={(e) => e.target.previousSibling.click()}>
          사진 업로드
        </button>
      </div>

      <div className="file-container">
        {selectedImage && (
          <div className="file-wrapper">
            <img src={previewUrl} alt="Uploaded file" />
            <div className="delete-button" onClick={deleteImage}>
              <HighlightOffIcon fontSize="large" color="error" />
            </div>
          </div>
        )}
      </div>

      <div>
        <label>카테고리</label>
        <br />
        <select value={selectedCategory} onChange={(e) => setSelectedCategory(Number(e.target.value))} className="mx-auto">
          {categoryOptions.map((option) => (
            <option key={option.id} value={option.id}>
              {option.label}
            </option>
          ))}
        </select>
      </div>
      <br />

      <div>
        <label>제목</label>
        <br />
        <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
      </div>
      <br />

      <div>
        <label>소요 시간</label>
        <br />
        <input
          type="text"
          value={timeValue}
          onChange={(e) => {
            const inputValue = e.target.value;
            // 입력 값 유효성 검사: 0으로 시작하지 않는 숫자만 허용
            if (/^[1-9][0-9]*$/.test(inputValue) || inputValue === "") {
              setTimeValue(inputValue);
            }
          }}
        />
      </div>
      <br />

      <div>
        <label>필요 재료</label>
        <br />
        <textarea
          className="mx-auto"
          name=""
          id=""
          cols="30"
          rows="5"
          value={materialValue}
          onChange={(e) => setMaterialValue(e.target.value)}
        ></textarea>
      </div>
      <br />

      <div>
        <label>내용 입력</label>
        <br />
        <textarea
          className="mx-auto"
          name=""
          id=""
          cols="30"
          rows="5"
          value={contentValue}
          onChange={(e) => setContentValue(e.target.value)}
        ></textarea>
      </div>
      <br />

      <div>
        <button className="btnn" onClick={handleFileUpload} style={{ marginBottom: "60px" }}>
          파일 업로드
        </button>
      </div>
    </div>
  );
}

export default UploadRecipes;
