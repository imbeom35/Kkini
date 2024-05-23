import React, { useEffect, useState } from "react";
import { ProfileUserDetails } from "../../components/mypage/ProfileUserDetails";
import ReqUserPostPart from "../../components/mypage/ReqUserPostPart";
import { useParams } from "react-router-dom";

function N5Mypage() {
  window.scrollTo(0, 0);
  const { userId = "" } = useParams();
  const [mine, setMine] = useState(1);
  const [memid, setMemid] = useState(userId || "mypage");

  useEffect(() => {
    if (userId !== "") {
      setMine(0);
      setMemid(userId);
    }
  }, [userId, mine]);

  return (
    <div>
      {/* 프로필 */}
      <div className="w-full">
        <div className="">
          <ProfileUserDetails 내것={mine} memid={memid}></ProfileUserDetails>
        </div>
        <div>
          <ReqUserPostPart 내것={mine} memid={memid}></ReqUserPostPart>
        </div>
      </div>
    </div>
  );
}

export default N5Mypage;
