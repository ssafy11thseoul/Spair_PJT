<template>
    <div id="create-container">
        <h4>게시글 등록</h4>
        <div id="form-container">
            <div id="register">
                <div>
                    <label id="nickname-label" for="nickname">작성자 </label>
                    <input type="text" id="nickname" :value="userNickname" readonly>
                </div>
                <div>
                    <label id="title-label" for="title">제목  </label>
                    <input type="text" id="title" v-model="board.post.title">
                </div>

                <!-- 모집 정보 -->
                <div id="infos">
                    <div>
                        <label for="status">모집상태  </label>
                        <select name="status" id="status" v-model="board.post.status">
                            <option value="1" >모집중</option>
                            <option value="0">모집완료</option>
                        </select>
                    </div>
                    <div>
                        <label for="gender">모집성별  </label>
                        <select name="gender" id="gender" v-model="board.post.gender">
                            <option value="" disabled>모집성별</option>
                            <option value="1">남성</option>
                            <option value="2">여성</option>
                            <option value="3">상관없음</option>
                        </select>
                    </div>
                    <div>
                        <label for="recruitment_num">모집인원  </label>
                        <select name="recruitment_num" id="recruitment_num" v-model="board.post.recruitmentNum">
                            <option value="" disabled>모집인원</option>
                            <option value="1명">1명</option>
                            <option value="2명">2명</option>
                            <option value="3명 이상">3명 이상</option>
                            <option value="상관없음">상관없음</option>
                        </select>
                    </div>
                    <div>
                        <label for="exercise_type">운동종류  </label>
                        <select name="exercise_type" id="exercise_type" v-model="board.post.exerciseType">
                            <option value="" disabled>운동종류</option>
                            <option value="PT">PT</option>
                            <option value="필라테스">필라테스</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                    <div id="price-wrap">
                        <label for="price">가격</label>
                        <input type="price" id="price" placeholder="1인당 가격을 입력하세요" v-model="board.post.price">
                        <span>원</span>
                    </div>
                </div>

                <!-- 도로명 주소 -->
                <div id="address">
                    <label for="road_address">스포츠시설 주소</label>
                    <div class="search_wrap">
                        <input type="road_address" id="road_address" placeholder="주소" :value="address" readonly>
                        <div class="address_search_btn" @click="searchAddress">주소찾기</div>
                    </div>
                    <input type="text" name="detail_address" id="detail_address" placeholder="상세주소" v-model="detailAddress" @change="insertDetailAddress">
                </div>
                
                <div id="area">
                    <label for="content">내용</label>
                    <textarea id="content" rows="10" v-model="board.post.content"></textarea>
                </div>
                <div id="regi-btn">
                    <button @click="boardCreate">등록</button>
                </div>
                </div>
            </div>
        </div>
    </template>

<script setup>
    import { useBoardStore } from '@/stores/board'
    import { onMounted, ref } from 'vue'
    import axios from 'axios';

    const store = useBoardStore()
    const userNickname = ref()
    const detailAddress = ref()
    const address = ref()

    const board = ref({
        "post": {
          "userId": "",
          "title": "",
          "content": "",
          "status": 1,
          "exerciseType": "",
          "price": "",
          "gender": "",
          "recruitmentNum": "",
        },
        "sportsCenter": {
          "roadAddress": "",
          "localAddress": "",
          "latitude": "",
          "longitude": ""
        }
    })

    onMounted(() => {
        const localStorageData = JSON.parse(localStorage.getItem("loginUserInfo")) 
        userNickname.value = localStorageData.nickname
        board.value.post.userId = localStorageData.userId
    })

    const searchAddress = () => {
        new daum.Postcode({
            oncomplete: function(data) {
                address.value = data.address
                board.value.sportsCenter.roadAddress = data.address
                board.value.sportsCenter.localAddress = data.jibunAddress
            }
        }).open()
    }

    const insertDetailAddress = () => {
        if(!board.value.sportsCenter.localAddress) {
            alert("주소찾기를 먼저 진행해주세요.")
            detailAddress.value = ""
        } else {
            board.value.sportsCenter.localAddress = board.value.sportsCenter.localAddress + " " + detailAddress.value
            board.value.sportsCenter.roadAddress = board.value.sportsCenter.roadAddress + " " + detailAddress.value
        }
    }

    // 사용자가 입력한 주소로부터 위도, 경도 값을 계산
    const getCoordinate = async() => {
        await axios({
            url : 'https://dapi.kakao.com/v2/local/search/address.json?query=' + encodeURI(address.value),
            method : 'GET',
            headers : {
                Authorization : `KakaoAK ${import.meta.env.VITE_KAKAO_REST_API_KEY}`
            }
        }).then((response) => {
            const result = response.data.documents[0]
            board.value.sportsCenter.latitude = result.y
            board.value.sportsCenter.longitude = result.x
        }).catch(() => {
            alert("정확한 주소를 입력하였는지 확인해주세요.")
        })
    }

    const boardCreate = async function(){
        if(!board.value.post.title || !board.value.post.content || !board.value.post.exerciseType 
            || !board.value.post.price || !board.value.post.gender || !board.value.post.recruitmentNum
            || !board.value.sportsCenter.roadAddress) {
            alert("입력되지 않은 칸이 있습니다.")
            return
        } 
        
        else if(!Number(board.value.post.price)) {
            alert("가격은 숫자만 입력해주세요.")
            return
        }

        else {
            await getCoordinate()
            store.createBoard(board.value)
        }
    }
</script>

<style scoped>
    #create-container {
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    h4 {
        width: 75%;
        max-width: 1200px;
        min-width: 1000px;
        display:flex;
        justify-content: start;
        align-items: center;
        font-family: 'Tenada';
        font-size: 1.5rem;
        margin: 1rem;
    }

    #form-container {
        width: 75%;
        max-width: 1200px;
        min-width: 1000px;
        border: 0.1rem solid var(--gray-color);
        box-shadow: 0.1rem 0.5rem 0.5rem var(--gray-color);
    }

    #register {
        display: flex;
        flex-direction: column;
        justify-content: start;
        align-items: start;
        font-family: 'NanumSquareRound';
        font-size: 1rem;
        margin: 3rem;
    }

    #register > div {
        margin-bottom: 0.3rem;
        width: 100%; 
        display: flex;
        margin-bottom: 0.6rem;
        margin-bottom: 1rem;
    }

    #nickname-label {
        width: 5.2rem;
    }

    #title-label {
        width: 5.4rem;
    }

    #title {
        width: 90%;
    }

    #infos {
        display: flex;
        justify-content: space-between;
        align-items: end;
        height: 2rem
    }

    #infos div:nth-child(5) {
        width: 25%;
    }

    #infos label {
        margin: 0 0.5rem 0 0;
    }

    #price-wrap input{
        width: 70%;
        /* margin-right: 5px */
    }
    
    label {
        width: 12%;
        font-size: 1.2rem;
        font-weight: bold;
    }

    textarea {
        width: 100%;
        resize: none;
        overflow-y: auto; 
        overflow-x: hidden;
        font-size: 1.2rem;
        padding: 5px;
        font-family: 'NanumSquareRound';
        box-sizing: border-box;
    }

    #area {
        display: flex;
        flex-direction: column;
    }

    #area label {
        margin: 5px 0 ;
    }

    input {
        width: 88%;
        outline: none;
        border-style: none;
        border-bottom: 0.1rem solid #000000;
    }

    input[id="nickname"] {
        border: none;
    }

    #address {
        display: flex;
        flex-direction: column;
    }

    #address label {
        width: 30%;
    }

    #regi-btn {
        display: flex;
        flex-direction: row-reverse;
    }

    .search_wrap {
        display: flex ;
        margin-top: 5px
    }

    .search_wrap input {
        margin-right: 10px ;
        height: 30px;
    }

    .address_search_btn {
        width: 10%;
        height: 30px;
        display: flex ;
        justify-content: center;
        align-items: center;
        border-radius: 10px;
        font-weight: 600 ;
        font-size: 0.9rem ;
        background-color: var(--primary-color);
        font-family: 'NanumSquareRound';
        cursor: pointer;
        color: #FFFFFF;
    }

    #detail_address {
        margin-top: 10px ;
        padding-bottom: 10px;
    }

    button {
        border-style: none;
        background-color: var(--secondary-color);
        font-size: 1.2rem;
        border-radius: 0.5rem;
        width: 7rem;
        height: 2.5rem;
        font-weight: bold;
        cursor: pointer;
    }
</style>