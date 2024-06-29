/*
    ORACLE 계정(SYSTEM) 연결
        Name : SYSYEM
        사용자 이름 : sys as sysdba
        비밀번호 : 1234
    * SYSTEM OR SYS 계정에서만 계정생성 가능
    
    1. DB 계정생성
        : CREATE USER [id] IDENTIFIED BY [PW];
      * 계정 삭제 : DROP USER [id] CASCADE;
    
    2. 권한부여
      - 위에서 만든 사용자 계정에게 최소한의 권한 부여(데이터관리, 접속) 
        : GRANT RESOURCE, CONNECT TO [id];
      * 권한 취소 : REVOKE [권한] FROM [id];
*/
-- 계정 생성 및 권한 부여
CREATE USER PETTOYSHOP IDENTIFIED BY PETTOYSHOP;
GRANT RESOURCE, CONNECT TO PETTOYSHOP;

-- ======================================================================
/*
    3. 테이블 생성 및 삭제
      - 생성 : CREATE TABLE "[DB계정명]"."[테이블명]" (
                [컬럼명] [자료형]([크기]) [DEFAULT 기본값] [제약조건],
                ...
              );
        * DEFAULT SYSDATE : 기본값 지정 / INSERT 시 해당 컬럼에 값이 없다면 현재시간 넣어줌
      - 삭제 : DROP TABLE [테이블명];
      
    4. 컬럼 주석
            : COMMENT ON COLUMN [테이블명].[컬럼명] IS '[주석 내용]';
        
    5. 참고    
        - DESC [테이블명]: 만든 테이블 확인
            : DESC PETTOYSHOP.MEMBER;
        - USER_TABLES : 사용자가 가지고 있는 테이블들의 전반적 구조 확인하는 뷰 테이블
            : SELECT * FROM USER_TABLES WHERE TABLE_NAME = 'MEMBER';
        - USER_TAB_COLUMNS : 테이블, 뷰의 컬럼과 관련된 정보 조회하는 뷰 테이블
            : SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'MEMBER';            

*/
-- 테이블 생성(추가 후 새로고침해야 보임)
CREATE TABLE "PETTOYSHOP"."MEMBER" (
    MEMBER_ID VARCHAR2(20),
    MEMBER_PWD VARCHAR2(20),
    MEMBER_NAME VARCHAR2(20),
    REG_TM DATE DEFAULT SYSDATE
);
-- 테이블 삭제
DROP TABLE "PETTOYSHOP"."MEMBER";

SELECT * FROM MEMBER;
-- 임시데이터 INSERT
INSERT INTO MEMBER (MEMBER_ID, MEMBER_PWD, MEMBER_NAME) VALUES ('USER1', '1234', '홍길동');
COMMIT;

select * from v$version;















