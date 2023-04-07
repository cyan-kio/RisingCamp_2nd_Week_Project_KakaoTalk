# RisingCamp_2nd_Week_Project_KakaoTalk_Clone
Activity LifeCycle을 학습하여 정리하고,<br>
(링크: https://www.notion.so/softsquared/2-21d9694918734379a9f1d54642089d11?pvs=4)<br>
각 LifeCycle을 활용하여 KakaoTalk을 클론해보았다.

## 시연영상 및 화면
- 자동 로그인 구현
<p align="center">
  <img width="30%" src="https://user-images.githubusercontent.com/85236336/230571761-43cf562a-cecc-4f84-b638-775a0d1866fa.gif">
</p>
<br>

- 최근 메뉴 저장 및 스크롤 위치 저장
<p align="center">
  <img width="30%" src="https://user-images.githubusercontent.com/85236336/230571911-2da30cc0-8da1-4d24-9e42-9c21a2c343f5.gif">
</p>
<br>

- 카메라 인텐트 접근 및 오버뷰
<p align="center">
  <img width="30%" src="https://user-images.githubusercontent.com/85236336/230571848-ca523f62-1bc1-4c6c-b3ef-08a9e2981eb8.gif">
</p>
<br>

### 앱 분석

앱을 분석한 결과 ‘카카오톡’은 다음의 기능들을 가지고 있었다.

- 자동 로그인 : 초기 로그인을 완료하면 앱을 재실행시 로그인을 수행하지 않는다.
- 최근 메뉴 저장: 앱 재실행시 최근 접근한 메뉴의 화면을 보여준다.
- 스크롤 위치 저장: 메뉴 전환시 이전 스크롤의 위치를 보여준다.
- 카메라 기능 중 오버뷰: 카메라 기능 활용 중 오버뷰 수행시 카메라 화면이 나오지 않도록 한다.

### 기능 설계

이번 프로젝트에서는 Activity LifeCycle을 활용하여 위 네 기능을 구현해보려고 시도하였다. 각 기능은 다음과 같이 구현해보고자 하였다.

- 자동 로그인
    
    SharedPreference의 데이터 파일에 회원의 Id, Pw 값을 저장해두고, onResume() 콜백에 사용자로부터 입력받은 값과 비교하여 로그인을 수행하는 로직을 작성하였다. 처음 로그인 성공 시 로그인 상태 값을 데이터 파일에 저장한다. 앱 재실행시 onCreate()에서 로그인 상태 값을 확인하고, 확인이 완료되면 친구 목록 화면으로 전환시킨다.
    
- 최근 메뉴 저장
    
    onResume()에 BottomNavigationView의 Fragment 화면 전환 로직을 작성하였다. onStop()에는 SharedPreference를 이용해 접근한 Item Id 값을 데이터 파일에 저장한다. onStart()에서는 앱 재실행시 저장한 Item Id를 받아 해당 Fragment를 띄우고, Bottom~View의 selectedItemId로 설정하여 어떤 메뉴의 화면인지를 표시해주었다.
    
- 스크롤 위치 저장 ⇒ 구현 실패
    
    onStop()에서 SharedReference를 이용해 데이터 파일에 ScrollView.scrollY 값을 받아 저장하였다.  onViewStateRestored()에는 저장된 scrollY 값을 받아와 ScrollView의 scrollTo()로 스크롤 위치를 변경하도록 설정하였다.
    
    → 작성한 코드를 모두 지워보았는데, Fragment 전환시에도 대상 Fragment의 스크롤 위치가 저장되는 것을 확인할 수 있었다. 다른 분들의 프로젝트에서는 RecyclerView를 이용했을때 스크롤 위치가 저장되지 않았는데, 기본적으로 제공되는 ContainerView로만 구성해서 그런 것 같다. RecyclerView로 목록을 작성할때도 스크롤 위치가 동일하게 작동하는지 확인해볼 필요가 있겠다.
    
    - LifeCycle을 연습하고 싶어서 추가로, 앱을 재실행했을 경우 스크롤 위치를 불러오는 것을 구현해보고 싶어 시도하였다. scrollY 값은 저장이 되었지만 scrollTo로는 적용이 안되었다.
    
- 카메라 기능 중 오버뷰 ⇒ 구현 실패
    
    암시적 인텐트를 이용해 카메라 앱을 불러오고자 했기 때문에 제어할 수 있는 부분이 아니었다.
    

### LifeCycle 활용

- onCreate()
    - Activity의 화면 Layout 정의 및 속성 설정, View Binding 및 SharedReferences 선언, BottomNavigationView 속성 설정, Fragment 선언, StatusBar 설정, 로그안 상태 확인 등
- onStart()
    - BottomNavigationView와 Fragment 연결(초기 설정) 및 저장된 Fragment 호출
    - 저장된 Scroll y 축 값 불러오는 작업
- onResume()
    - BottomNavigationView.setOnItemSelectedListener{} 정의(Fragment 화면 전환)
    - 로그인 로직 작성(로그인 상태 값 저장)
- onPause()
    - 카메라 해제 → 활용 못함
- onStop()
    - BottomNavigationView가 접근한 마지막 Fragment ID 저장
    - 스크롤 y축 저장
- onDestroy()
    - 카메라 앱을 제어하고자 했지만 인텐트였기에 시도도 못했다 → 활용 못함
    - Fragment LifeCycle에서 binding이라도 해제

### 화면 구성

- 로그인 화면(Activity)
- 계정 등록 액티비티(Activity)
- 스플래쉬 화면(Activity)
- 메인 액티비티(BottomNavigationView + 하위 프래그먼트들로 구성)
    - 친구 목록 화면(프래그먼트)
    - 채팅 목록 화면(프래그먼트)
    - 뷰 화면(프래그먼트)_생략 예정
    - 쇼핑 화면(프래그먼트)_생략 예정
    - 더보기 화면(프래그먼트)_생략 예정
- 채팅 액티비티(채팅 목록 화면을 통해 접근)
    - 하단 메뉴의 카메라 기능을 통해 카메라 인텐트 접근
