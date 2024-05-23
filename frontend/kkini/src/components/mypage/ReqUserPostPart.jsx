import React, { useEffect, useState } from 'react';
import { AiOutlineTable } from 'react-icons/ai';
import { BiBookmark, BiBookBookmark } from 'react-icons/bi';
import { MdLocalDining } from 'react-icons/md';

import P1Post from './P1_post.jsx';
import P2Recipe from './P2_recipe.jsx';
import P3Book from './P3_book.jsx';
import P4Scrap from './P4_scrap.jsx';

const ReqUserPostPart = ({ 내것 = 0, memid = 0 }) => {
  const [tab, setTab] = useState(0);
  const [tabs, setTabs] = useState([
    { tab: "포스트", icon: <AiOutlineTable />, ind: 0 },
    { tab: "레시피", icon: <MdLocalDining />, ind: 1 },
    { tab: "도감", icon: <BiBookBookmark />, ind: 2 },
    { tab: "스크랩", icon: <BiBookmark />, ind: 3 },
  ]);
  const [activeTab, setActiveTab] = useState(tabs[0].tab);
  
  useEffect(()=> {
    if (내것 !== 1) {
      setTabs([
        { tab: "포스트", icon: <AiOutlineTable />, ind: 0 },
        { tab: "레시피", icon: <MdLocalDining />, ind: 1 }
      ])
    }
  }, [내것]);

  return (
    <div>
      <div className="flex justify-between border-t relative mx-auto px-2">
        {tabs.map((item) => (
          <div
            key={item.tab}
            onClick={() => {
              setActiveTab(item.tab);
              setTab(item.ind);
            }}
            className={`${
              activeTab === item.tab ? "border-t border-black" : "opacity-60"
            } flex items-center cursor-pointer py-2 text-sm mx-auto`}
          >
            <p style={{ marginRight: '12px' }}>{item.icon}</p> 
            <p className='ml-1'>{item.tab}</p>
          </div>
        ))}
      </div>
      <TabContent tab={tab} />
    </div>
  );
};

function TabContent({ tab }) {
  switch (tab) {
    case 0:
      return <P1Post />;
    case 1:
      return <P2Recipe />;
    case 2:
      return <P3Book />;
    case 3:
      return <P4Scrap />;
    default:
      return null;
  }
}

export default ReqUserPostPart;