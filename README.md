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

### PostgreSQL 설치

    ~$ sudo apt install postgresql -y
    ~$ sudo vim /etc/postgresql/14/main/pg_hba.conf
    	# :set number
    	# /IPv4 local
    	# 96 + Shift + g
    	# IPv4 local connections의 ADDRESS를 0.0.0.0/0으로 수정
    	# 인증 방식 변경
    	# # "local" is for Unix domain socket connections only
    	# local   all             all                                     peer
    	# peer: 운영체제(우분투)의 사용자 이름과 PostgreSQL의 사용자 이름이 같아야 인증이 되는 방식
    	#       암호 없이 로그인할 수 있으나 시스템 계정을 매번 만들어야 한다는 점에서 번거러움
    	# local   all             all                                     scram-sha-256
    	# peer -> scram-sha-256으로 변경
    
    ~$ sudo vim /etc/postgresql/14/main/postgresql.conf
    	# :set number
    	# /listen : listen 단어 검색
    	# 60 + Shift + g : 60번 줄로 이동
    	# 주석 제거 후 listen_addresses를 '*'로 수정
    
    ~$ sudo -u postgres psql

---

### Java 및 Spring Boot 환경 세팅

    ~$ sudo passwd
        1234
    ~$ sudo apt update && sudo apt upgrade -y
    ~$ sudo apt install -y tzdata
    ~$ sudo dpkg-reconfigure tzdata
    ~$ sudo date
    ~$ export TZ=Asia/Seoul
    ~$ sudo apt-get install openjdk-17-jdk (창 나오면 엔터 혹은 →, 엔터)
    ~$ vim ~/.bashrc

    최하단에 추가

    export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
    export PATH=$PATH:$JAVA_HOME/bin

    ~$ source ~/.bashrc
    ~$ echo $JAVA_HOME

    ~$ sudo fallocate -l 2G /swapfile
    ~$ sudo chmod 600 /swapfile
    ~$ sudo mkswap /swapfile
    ~$ sudo swapon /swapfile
    ~$ echo '/swapfile none swap sw 0 0' | sudo tee -a /etc/fstab
    ~$ free -h

    ~$ git clone
    ~$ chmod +x ./gradlew
    ~$ ./gradlew build
    ~$ java -jar build/libs/app.jar

    ~$ sudo apt install iptables-persistent
    ~$ sudo iptables -t nat -A PREROUTING -p tcp --dport 80 -j REDIRECT --to-ports 10000
    ~$ sudo chmod 777 /etc/iptables/rules.v4
    ~$ sudo iptables-save > /etc/iptables/rules.v4
    ~$ cat /etc/iptables/rules.v4


---


### ⚠️ 주의사항

    - .pem 파일 절대 GitHub에 업로드하지 말 것
    - PostgreSQL 설정 시 sudo 권한이 필요함
    - 포트 번호는 프로젝트 환경에 맞게 변경 가능





<p align="right">(<a href="#readme-top">back to top</a>)</p>
