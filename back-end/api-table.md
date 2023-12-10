# - API Table

## 1. Location
| Method | Resource | Header | Body | Response | Description |
| :---: | :--- | :--- | :--- | :--- | :--- |
| GET | /location/search/in-range?latitude={DOUBLE}&longitude={DOUBLE}&range={DOUBLE} | | | [INT]locationId,  [DOUBLE]latitude,  [DOUBLE]longitude,  [STRING]category,  [INT]visitCount | 범위로 주변 장소 찾기 |
| GET | /location/search/within-range?latitude={DOUBLE}&longitude={DOUBLE}&range={DOUBLE}&minRange={DOUBLE} | | | [INT]locationId,  [DOUBLE]latitude,  [DOUBLE]longitude,  [STRING]category,  [INT]visitCount | 최소 범위 이상, 최대 범위 이하의 장소 찾기 |
| GET | /location/search/information?latitude={DOUBLE}&longitude={DOUBLE}&category={STRING} | | | [STRING]title, [STRING]description, [STRING]images[] | 해당하는 장소의 이름과 설명, 이미지 조회 |
| GET | /location/search/recommend-location?latitude={DOUBLE}&longitude={DOUBLE},range={DOUBLE},category={STRING} | | | [INT]locationId, [DOUBLE]latitude, [DOUBLE]longitude, [STRING]category | 해당하는 장소의 이름과 설명 조회 |
| POST | /user/location/register | Authorization{STRING} | latitude{DOUBLE}, longitude{DOUBLE}, category{STRING}, title{STRING}, description{STRING}, image{STRING} | STATUS | 장소 등록 |
| POST | /user/location/agree | Authorization{STRING} | locationId{INT} | STATUS | 장소 추천 |
| POST | /user/location/edit | Authorization{STRING} | locationId{INT}, title{STRING}, image{STRING} | STATUS | 장소 정보 수정 (title, image) |
| POST | /user/location/delete | Authorization{STRING} | locationId{INT} | STATUS | 장소 삭제 |

### 1.1 장소 범위 검색
| Key | Value |
| :--- | :--- |
| Method | GET |
| Resource | /location/search/in-range |
| URL Query | latitude{DOUBLE}, longitude{DOUBLE}, range{DOUBLE} |
| Header | |
| Body | |
| Response | [INT]locationId,  [DOUBLE]latitude,  [DOUBLE]longitude,  [STRING]category,  [INT]visitCount |
- range는 m(Meter) 단위이다.
- 범위 계산 공식은 다음과 같다.
  - maxLatitude = 1 / 111,000 * range
  - maxLongitude = COS(latitude) * maxLatitude

### 1.2 장소 범위 검색 (최소, 최대)
| Key | Value |
| :--- | :--- |
| Method | GET |
| Resource | /location/search/within-range |
| URL Query | latitude{DOUBLE}, longitude{DOUBLE}, range{DOUBLE}, minRange{DOUBLE} |
| Header | |
| Body | |
| Response | [INT]locationId,  [DOUBLE]latitude,  [DOUBLE]longitude,  [STRING]category,  [INT]visitCount |
- range는 m(Meter) 단위이다.
- 범위 계산 공식은 다음과 같다.
  - maxLatitude = 1 / 111,000 * range
  - maxLongitude = COS(latitude) * maxLatitude
  - minLatitude = 1 / 111,000 * minRange
  - minLongitude = COS(latitude) * minLatitude

### 1.3 장소 정보 조회 (장소 이름, 장소 설명, 이미지)
| Key | Value |
| :--- | :--- |
| Method | GET |
| Resource | /location/search/information |
| URL Query | latitude{DOUBLE}, longitude{DOUBLE}, category{STRING} |
| Header | |
| Body | |
| Response | [STRING]title, [STRING]description, [STRING]images[] |

### 1.4 장소 등록
| Key | Value |
| :--- | :--- |
| Method | POST |
| Resource | /user/location/register |
| URL Query | |
| Header | Authorization{STRING} |
| Body | latitude{DOUBLE}, longitude{DOUBLE}, category{STRING}, title{STRING}, description{STRING}, image{STRING} |
| Response | STATUS |

### 1.5 장소 삭제
| Key | Value |
| :--- | :--- |
| Method | POST |
| Resource | /user/location/delete |
| URL Query | |
| Header | Authorization{STRING} |
| Body | locationId{INT} |
| Response | STATUS |

---
## 2. User
| Method | Resource | Header | Body | Response | Description |
| :---: | :--- | :--- | :--- | :--- | :--- |
| GET | /user/search/similar-user | Authorization{STRING} | | [STRING]userId, [STRING]nickName, [STRING]image | 관심 일치 유저 찾기 |
| POST | /user/edit/profile | Authorization{STRING} | nickName{STRING}, image{STRING} | STATUS | 유저 정보 수정 |
| POST | /user/edit/category-list | Authorization{STRING} | categoryList{STRING} | STATUS | 유저 카테고리 리스트 설정 |

### 2.1 관심 일치 유저 찾기
| Key | Value |
| :--- | :--- |
| Method | GET |
| Resource | /user/search/similar-user |
| URL Query | |
| Header | Authorization{STRING} |
| Body | |
| Response | [STRING]userId, [STRING]nickName, [STRING]image |
- 자신과 최대한 비슷한 카테고리 리스트를 가진 유저를 추천해준다.
- 카테고리 리스트가 비슷할 수록 뽑힐 확률이 높은 랜덤 함수를 돌린다.
  - 즉, 항상 같은 유저가 나오지 않는다.
  - 카테고리 리스트를 등록하고 사용할 것.

### 2.2 유저 정보 수정
| Key | Value |
| :--- | :--- |
| Method | POST |
| Resource | /user/edit/profile |
| URL Query | |
| Header | Authorization{STRING} |
| Body | nickName{STRING}, image{STRING} |
| Response | STATUS |
- 유저의 닉네임과 프로필 사진을 수정한다.
- 변경하고 싶은 내용만 JSON으로 보내주면 된다.
  - 닉네임만 변경하고 싶을 때
    - "nickName":"변경할 닉네임"
  - 이미지만 변경하고 싶을 때
    - "image":"변경할 이미지 base64 인코딩"
  - 닉네임과 이미지 둘 다 변경하고 싶을 때
    - "nickName":"변경할 닉네임"
    - "image":"변경할 이미지 base64 인코딩"

### 2.3 유저 카테고리 리스트 설정
| Key | Value |
| :--- | :--- |
| Method | POST |
| Resource | /user/edit/category-list |
| URL Query | |
| Header | Authorization{STRING} |
| Body | categoryList{STRING} |
| Response | STATUS |
- 유저의 카테고리 리스트를 수정한다.
- 카테고리 리스트로 설정할 카테고리들을 ','(쉼표)로 구분하여 줄글로 보내주면 된다.
- 예시:
  - "categoryList":"화장실,쓰레기통,흡연장,멋진 장소"
- 쉼표 앞뒤로는 띄어쓰기가 있으면 안된다.
- 카테고리 사이에는 띄어쓰기가 있어도 된다.
