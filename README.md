## AWS EC2 서버 세팅 및 Spring Boot 배포 가이드

---

### Ubuntu 기본 세팅

    ~$ sudo passwd
        1234
    ~$ su root
        1234
    ~$ su ubuntu
    ~$ sudo apt update && sudo apt upgrade -y
    ~$ sudo apt install -y tzdata
    ~$ sudo dpkg-reconfigure tzdata
    ~$ date
    ~$ sudo fallocate -l 2G /swapfile
    ~$ sudo chmod 600 /swapfile
    ~$ sudo mkswap /swapfile
    ~$ sudo swapon /swapfile
    ~$ echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab
    ~$ free -h

---

### PostgreSQL 설치 및 설정

    ~$ sudo apt install postgresql -y
    ~$ sudo vim /etc/postgresql/14/main/pg_hba.conf
        # IPv4 local connections의 ADDRESS를 0.0.0.0/0으로 수정
        # 인증 방식 peer → scram-sha-256 으로 변경

    ~$ sudo vim /etc/postgresql/14/main/postgresql.conf
        # listen_addresses = '*' 로 수정

    ~$ sudo -u postgres psql

---

### Java 및 Spring Boot 환경 세팅

    > sudo apt-get install openjdk-17-jdk
    > vim ~/.bashrc

    # bashrc 파일 최하단 추가
    export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
    export PATH=$PATH:$JAVA_HOME/bin

    > source ~/.bashrc
    > echo $JAVA_HOME

---

### 스왑 메모리 설정

    > sudo fallocate -l 2G /swapfile
    > sudo chmod 600 /swapfile
    > sudo mkswap /swapfile
    > sudo swapon /swapfile
    > echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab
    > free -h

---

### Spring Boot 애플리케이션 빌드 및 실행

    > git clone [레포지토리주소]
    > chmod +x ./gradlew
    > ./gradlew build
    > java -jar build/libs/app.jar

---

### 포트 포워딩

    > sudo apt install iptables-persistent
    > sudo iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-ports 10000
    > sudo chmod 777 /etc/iptables/rules.v4
    > sudo iptables-save > /etc/iptables/rules.v4
    > cat /etc/iptables/rules.v4

---


<p align="right">(<a href="#readme-top">back to top</a>)</p>
