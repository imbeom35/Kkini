import React from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/swiper-bundle.min.css";

function ImageSwiper({ postImage }) {
  return (
    <Swiper pagination={true} className="mySwiper" style={{ width: "100%", height: "100%" }}>
      {postImage.map((link, index) => (
        <SwiperSlide key={index}>
          <div style={{ display: "flex", alignItems: "center", justifyContent: "center", height: "100%" }}>
            <img style={{ borderRadius: "2%" }} src={link} alt={`포스트 이미지 ${index}`} />
          </div>
        </SwiperSlide>
      ))}
    </Swiper>
  );
}

export default ImageSwiper;
