## AWS EC2 서버 세팅 및 배포 가이드

     이 문서는 Ubuntu 서버 초기 세팅부터 
     Java(Spring Boot) 애플리케이션 배포까지의 과정을 정리한 가이드입니다.


#### 기본 설정 (Ubuntu 초기 세팅)
     Ubuntu에서 서버를 처음 세팅할 때 필요한 환경 구성입니다.

     sudo passwd
     # 비밀번호 설정 (예: 1234)
     sudo apt update && sudo apt upgrade -y
     sudo apt install -y tzdata
     sudo dpkg-reconfigure tzdata
     sudo date
     export TZ=Asia/Seoul


#### Java 설치
     서버 실행을 위한 OpenJDK 17을 설치합니다.

     sudo apt-get install openjdk-17-jdk
     vim ~/.bashrc

     bashrc 파일의 마지막 줄에 아래 코드 추가

     export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
     export PATH=$PATH:$JAVA_HOME/bin

     적용 및 확인

     source ~/.bashrc
     echo $JAVA_HOME


#### Swap 메모리 설정
     메모리 부족을 방지하기 위해 가상 메모리를 생성합니다.

     sudo fallocate -l 2G /swapfile
     sudo chmod 600 /swapfile
     sudo mkswap /swapfile
     sudo swapon /swapfile
     echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab
     free -h


#### 애플리케이션 빌드 및 실행
     Spring Boot 애플리케이션을 빌드하고 실행합니다.

     git clone <your-repo-url>
     chmod +x ./gradlew
     ./gradlew build
     java -jar build/libs/app.jar


#### 포트 포워딩 (80 → 10000)
     HTTP 기본 포트(80) 요청을 애플리케이션 포트(10000)으로 리다이렉트합니다.

     sudo apt install iptables-persistent
     sudo iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-ports 10000
     sudo chmod 777 /etc/iptables/rules.v4
     sudo iptables-save > /etc/iptables/rules.v4
     cat /etc/iptables/rules.v4


#### PostgreSQL 설치 및 설정
     데이터베이스(PostgreSQL)를 설치하고 원격 접속을 허용합니다.

     sudo apt install postgresql -y
     sudo vim /etc/postgresql/14/main/pg_hba.conf

     pg_hba.conf 설정
          IPv4 local connections 항목의 ADDRESS를 0.0.0.0/0 으로 수정
          인증 방식(peer → scram-sha-256)으로 변경

     sudo vim /etc/postgresql/14/main/postgresql.conf

     postgresql.conf 설정
          listen_addresses = '*' 로 변경

     sudo -u postgres psql


#### Ubuntu 기본 세팅 요약
     전체 초기 세팅 명령어를 요약하면 다음과 같습니다.

     sudo passwd
     su root
     su ubuntu
     sudo apt update && sudo apt upgrade -y
     sudo apt install -y tzdata
     sudo dpkg-reconfigure tzdata
     date
     sudo fallocate -l 2G /swapfile
     sudo chmod 600 /swapfile
     sudo mkswap /swapfile
     sudo swapon /swapfile
     echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab
     free -h


<p align="right">(<a href="#readme-top">back to top</a>)</p>
