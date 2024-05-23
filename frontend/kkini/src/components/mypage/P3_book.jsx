import React from 'react';
import { useNavigate } from 'react-router-dom'

function P3Book() {
  window.scrollTo(0, 0);
  const navigate = useNavigate();

  return (
    <div>
        <img className='mx-auto' onClick={() => {navigate('/book')}} src="/img/도감버튼.png" alt="" 
          style={{ width: '300px'}}
        />
    </div>
  );
}

export default P3Book;
