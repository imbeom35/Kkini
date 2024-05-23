# jdk 이미지 불러오기
FROM  node:16.20.1

# 환경변수 설정
ENV REACT_APP_BASE_URL https://i9c210.p.ssafy.io/api
ENV REACT_APP_NAVER_LOGIN_URL https://i9c210.p.ssafy.io/api/oauth2/authorize/naver

# Docker Container에서 작업이 이루어지는 위치
WORKDIR /usr/src/app

COPY . .

RUN npm install
#RUN npm install -g serve
#RUN npm run build

EXPOSE 3000

# 번들 파일 실행하기 (컨테이너 실행할 때 동작)
#CMD ["serve", "-s", "build"]
CMD ["npm", "start"]