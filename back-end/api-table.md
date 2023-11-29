# - API Table

## 1. Location
| Method | Resource | Header | Body | Response | Description |
| :---: | :--- | :--- | :--- | :--- | :--- |
| GET | /location/search/in-range?latitude={DOUBLE}&longitude={DOUBLE}&range={DOUBLE} | | | [INT]locationId,  [DOUBLE]latitude,  [DOUBLE]longitude,  [STRING]category,  [INT]visitCount | 범위로 주변 장소 찾기 |
| GET | /location/search/within-range?latitude={DOUBLE}&longitude={DOUBLE}&range={DOUBLE}&minRange={DOUBLE} | | | [INT]locationId,  [DOUBLE]latitude,  [DOUBLE]longitude,  [STRING]category,  [INT]visitCount | 최소 범위 이상, 최대 범위 이하의 장소 찾기 |
| GET | /location/search/information?latitude={DOUBLE}&longitude={DOUBLE}&category={STRING} | | | [STRING]title, [STRING]description | 해당하는 장소의 이름과 설명 조회 |
| POST | /user/location/register | Authorization{STRING} | latitude{DOUBLE}, longitude{DOUBLE}, category{STRING}, title{STRING}, description{STRING}, image{BYTE[]} | STATUS | 장소 등록 |
| POST | /user/location/delete | Authorization{STRING} | latitude{DOUBLE}, longitude{DOUBLE}, category{STRING} | STATUS | 장소 삭제 |

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

### 1.3 장소 정보 조회 (장소 이름, 장소 설명)
| Key | Value |
| :--- | :--- |
| Method | GET |
| Resource | /location/search/information |
| URL Query | latitude{DOUBLE}, longitude{DOUBLE}, category{STRING} |
| Header | |
| Body | |
| Response | [STRING]title, [STRING]description |

### 1.4 장소 등록
| Key | Value |
| :--- | :--- |
| Method | POST |
| Resource | /user/location/register |
| URL Query | |
| Header | Authorization{STRING} |
| Body | latitude{DOUBLE}, longitude{DOUBLE}, category{STRING}, title{STRING}, description{STRING}, image{BYTE[]} |
| Response | STATUS |

### 1.5 장소 삭제
| Key | Value |
| :--- | :--- |
| Method | POST |
| Resource | /user/location/delete |
| URL Query | |
| Header | Authorization{STRING} |
| Body | latitude{DOUBLE}, longitude{DOUBLE}, category{STRING} |
| Response | STATUS |
