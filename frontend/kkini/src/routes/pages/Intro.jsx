import React from "react";
import "../../css/intro.css";
import 'animate.css';

export default function Intro() {
  return (
    <div style={{ 
      marginTop: "150px",
      backgroundImage: 'url(/img/밥상.PNG)',
      backgroundSize: 'cover',
      backgroundRepeat: 'no-repeat',
      backgroundPosition: 'center',
      width: "100%",
      height: "500px",
      }}>
      <img className="animate__animated animate__bounceInDown logo my-auto mx-auto" src="img/logo2.png" alt="로고" />
    </div>
  );
}
