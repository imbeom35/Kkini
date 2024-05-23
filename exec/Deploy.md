# Deploy
## Environment Variable
- CI: 빌드 중 warn 에러 발생시 CI/CD 중지 여부를 설정하는 변수. false로 설정.
- AWS_ACCESS_KEY: S3 사용을 위한 AWS 계정의 ACCESS KEY
- AWS_SECRET_KEY: S3 사용을 위한 AWS 계정의 SECRET KEY
- DB_ID: DB 유저 ID
- DB_PW: DB 유저 PW
- DOCKER_HUB_ID: 배포용 Docker Hub 계정의 ID
- DOCKER_HUB_NAME: 배포용 Docker Hub 계정의 이름
- DOCKER_HUB_PW: 배포용 Docker Hub 계정의 PW
- JWT_SECRET_KEY: JWT를 암호화/복호화하기 위한 비밀키
- NAVER_CLIENT_ID: 네이버 로그인 API를 사용하기 위한 개발자 계정의 ID
- NAVER_CLIENT_SECRET: 네이버 로그인 API를 사용하기 위한 개발자 계정의 비밀키

## Nginx Default File
```
	location / {
		proxy_pass http://172.17.0.1:3000;
	}

	location /api {
		proxy_pass http://172.17.0.1:8080;
	}
```

## Notice
1. 프로젝트를 성공적으로 세팅하기 위해 S3와 Naver API의 키가 필요하다.
2. 위에서 정의된 Environment Variable의 값을 등록해야 한다.
3. SSH 설치 이후 Default File에 각 요청 URL에 대해 포트를 분기하도록 설정해야 한다.
4. Docker와 GitLab Runner를 사용해 자동화 배포를 설정하는데, GitLab-Runner는 가장 먼저 컨테이너로 등록해주어야 한다.

## Nginx 설치
1. 우분투 시스템 패키지 업데이트
    > sudo apt update
2. nginx 패키지 설치
    > sudo apt install nginx
3. default.conf 설정
    > vi /etc/nginx/conf.d/default.conf

```
server {
	location / {
		proxy_pass http://172.17.0.1:3000;
	}

	location /api {
		proxy_pass http://172.17.0.1:8080;
	}
}
```

## Certbot 설치
1. snap으로 certbot 설치
    > sudo snap install certbot --classic
2. ssl 인증서 발급
    > sudo certbot --nginx
3. default.conf 변경사항 확인
```
server {
    server_name your.domain.com;

    location / {
        proxy_pass http://192.168.XXX.XXX:8080;
	proxy_set_header X-Real-IP $remote_addr;
	proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
	proxy_set_header Host $http_host;
    }

    listen 443 ssl; # managed by Certbot
    ssl_certificate /etc/letsencrypt/live/your.domain.com/fullchain.pem; # managed by Certbot
    ssl_certificate_key /etc/letsencrypt/live/your.domain.com/privkey.pem; # managed by Certbot
    include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
    ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot

}
server {
    if ($host = your.domain.com) {
        return 301 https://$host$request_uri;
    } # managed by Certbot

    listen 80;
    server_name your.domain.com;
    return 404; # managed by Certbot
}
```
4. 인증서 갱신을 위한 cronjob 생성
    > crontab -e

```
// 주석 가장 아래에 추가
0 0 * * * certbot renew --post-hook "sudo service nginx reload"
```

## 방화벽 설정
1. ssh 허용
    > sudo ufw allow ssh
2. http 허용
    > sudo ufw allow http
3. https 허용
    > sudo ufw allow https
4. 방화벽 가동
    > sudo ufw enable

## Docker 설치
1. 우분투 시스템 패키지 업데이트
    > sudo apt-get update
2. 필요한 패키지 설치
    > sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common
3. Docker의 공식 GPG키를 추가
    > curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
4. Docker의 공식 apt 저장소를 추가
    > sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
5. 시스템 패키지 업데이트
    > sudo apt-get update
6. Docker 설치
    > sudo apt-get install docker-ce docker-ce-cli containerd.io
7. Docker 설치 확인
    > sudo systemctl status docker

## GitLab Runner 설치
1. gitlab repository 추가
    > curl -L "https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.deb.sh" | sudo bash
2. gitlab-runner 설치
    > sudo apt install gitlab-runner
3. gitlab-runner 등록
    > sudo gitlab-runner register

    ```
   Q: Enter the GitLab instance URL (for example, https://gitlab.com/):  (GitLab URL 입력하기)
    I: (GitLab의 Runners 탭에서 확인한 URL)
    
    Q: Enter the registration token:  (GitLab Repository Token 입력하기)
    I: (GitLab의 Runners 탭에서 확인한 Token)
    
    Q: Enter a description for the runner:  (Runner Description 입력하기)
    I: my-runner
    
    Q: Enter tags for the runner (comma-separated):  (태그 입력하기, 여기서 입력된 태그는 gitlab-ci.yml파일에서 등록된 runner를 구분하기 위해 사용)
    I: ex) kkini-cicd
    
    Q: Enter an executor: docker-ssh+machine, custom, parallels, shell, virtualbox, docker+machine, docker, docker-ssh, ssh, kubernetes:  (빌드에 사용되는 executor 선택)
    I: ex) docker
    
    Q: Enter the default Docker image (for example, ruby:2.6):  (Docker image default 입력)
    I: docker:24.0.5  (본인의 docker 버전 입력)
   ```
4. gitlab-runner 연결 확인
    > gitlab-runner list
5. gitlab-runner 계정에 권한 부여
    > sudo usermod -aG docker gitlab-runner
6. dind 설정
    > vi /etc/gitlab-runner/config.toml

    ```
   [[runners]]
    [runners.docker]
    privileged = true
    volumes = ["/cache", "/var/run/docker.sock:/var/run/docker.sock"]
   ```
7. 도커 재시작
    > service docker restart