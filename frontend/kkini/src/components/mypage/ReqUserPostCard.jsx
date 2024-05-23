import React from 'react'
import { AiFillHeart } from 'react-icons/ai'
import { FaComment } from 'react-icons/fa'
import styled from 'styled-components';

const ReqUserPostCard = () => {
    return (
        <div className='p-2'>
            <PostContainer className='post w-56 h-56'>
            <img className='cursor-pointer' src="https://newsimg.sedaily.com/2023/04/04/29O67TZ4DD_1.jpg" alt="User post" />
            <Overlay>
                <OverlayText className='overlay-text flex justify-between'>
                    <div>
                        <AiFillHeart /><span>10</span>
                    </div>
                    <div>
                        <FaComment /><span>30</span>
                    </div>
                </OverlayText>
            </Overlay>
        </PostContainer>
        </div>
    )
}

export default ReqUserPostCard

const Overlay = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.5);
    opacity: 0;
    transition: opacity .2s ease-in-out;
`;

const PostContainer = styled.div`
    position: relative;
    overflow: hidden;

    &:hover ${Overlay} {
        opacity: 1;
    }

    img {
        display: block;
        width: 100%;
        height: 100%;
    }
`;

const OverlayText = styled.div`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    color: white;
    font-size: medium;
    text-align: center;
    width: 30%;
`;
