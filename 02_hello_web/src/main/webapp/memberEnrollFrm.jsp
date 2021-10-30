<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>유효성검사</title>
        <style>
            table#enroll {
                border: 1px solid;
                border-spacing: 10px;
                width: 500px;
                margin: 0 auto;
            }
            table#enroll th {
                text-align: right;
            }
            table#enroll td.btn-wrapper {
                text-align: center;
            }
            .phone {
                width: 30px;
            }
            span.req {
                color: red;
            }
            .short {
                width: 50px;
            }
        </style>
    </head>
    <body>
        <h3>회원가입 유효성검사</h3>
        <p>
            회원가입시 각 필드마다 유효성 검사를 하여 처리 할 수 있도록
            만들어라. <br />
            1. ID는 영어소문자,숫자만 가능해야함(4~12자리,소문자로 시작해야함)
            <br />
            2. 비밀번호는 숫자/문자/특수문자*!& 포함 형태의 8~15자리<br />
            3. 비밀번호와 비밀번호확인은 같아야 함<br />
            4. 이름은 무조건 한글만 가능해야 함(최소2글자 이상)<br />
            5. 주민번호는 자릿수6자리,7자리이고, 모두 숫자여야함.<br />
            6. 이메일은 @가 포함 되어야 함(@앞에가 영문자,숫자로 4~12글자 )
            <br />
            7. 전화번호는 첫번째 필드는 010만 입력 가능 해야 함 <br />
            두번째 필드는 3자리 이상 입력 해야 함 <br />
            세번째 필드는 무조건 4자리 입력 해야함 <br />
            (모두 숫자만 가능 해야함) <br />
        </p>
        <form action="<%= request.getContextPath() %>/member/enroll.do" name="memberFrm" method="POST">
            <table id="enroll">
                <tr>
                    <th>아이디<span class="req">*</span></th>
                    <td>
                        <input type="text" name="userId" id="userId" value="linux1"/>
                    </td>
                </tr>
                <tr>
                    <th>비밀번호<span class="req">*</span></th>
                    <td>
                        <input type="text" name="pwd" id="pwd" value="!@#$qwer"/>
                    </td>
                </tr>
                <tr>
                    <th>비밀번호확인</th>
                    <td>
                        <input type="text" id="pwdCheck" value="!@#$qwer"/>
                    </td>
                </tr>
                <tr>
                    <th>이름<span class="req">*</span></th>
                    <td>
                        <input type="text" name="userName" id="userName" value="리누스"/>
                    </td>
                </tr>
                <tr>
                    <th>주민번호<span class="req">*</span></th>
                    <td>
                        <input type="text" name="ssn1" id="ssn1" class="short" value="111111"/>-
                        <input type="text" name="ssn2" id="ssn2" class="short" value="1111111"/>
                    </td>
                </tr>
                <tr>
                    <th>이메일<span class="req">*</span></th>
                    <td>
                        <input type="email" name="email" id="email" value="linus@torvalds.org"/>
                    </td>
                </tr>
                <tr>
                    <th>전화번호<span class="req">*</span></th>
                    <td>
                        <input type="text" name="tel1" id="tel1" class="phone" value="010"/>-
                        <input type="text" name="tel2" id="tel2" class="phone" value="1234"/>-
                        <input type="text" name="tel3" id="tel3" class="phone" value="5678"/>
                    </td>
                </tr>
                <tr>
                    <th>직업</th>
                    <td>
                        <select name="job" id="job" value="디자이너">
                            <option value="공무원">공무원</option>
                            <option value="개발자">개발자</option>
                            <option value="무직">무직</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>취미</th>
                    <td>
                        <input type="checkbox" name="hobby" id="hobby1" value="reading" checked />
                        <label for="hobby1">독서</label>
                        <input type="checkbox" name="hobby" id="hobby2" value="development" />
                        <label for="hobby2">개발</label>
                        <input type="checkbox" name="hobby" id="hobby3" value="exercise" checked />
                        <label for="hobby3">운동</label>
                        <input type="checkbox" name="hobby" id="hobby4" value="game" />
                        <label for="hobby4">게임</label>
                        <input type="checkbox" name="hobby" id="hobby5" value="movie" />
                        <label for="hobby5">영화</label>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" class="btn-wrapper">
                        <input type="submit" value="제출" />&nbsp;
                        <input type="reset" value="초기화" />
                    </td>
                </tr>
            </table>
        </form>
        <script>
            document.memberFrm.onsubmit = function () {
                //1.아이디검사
                //첫글자는 반드시 영소문자로 이루어지고,
                //숫자가 하나이상 포함되어야함.
                //아이디의 길이는 4~12글자사이

                //2.비밀번호 확인 검사
                //숫자/문자/특수문자 포함 형태의 8~15자리 이내의 암호 정규식

                //비밀번호일치여부 검사

                //3.이름검사 : 한글2글자 이상만 허용.
                //4.주민번호체크

                //5.이메일 검사

                //6. 전화번호 검사

                if (!validateId({})) return false;
                if (!validatePassword({})) return false;
                if (!validatePasswordCheck({})) return false;
                if (!validateName({})) return false;
                if (!validateSSN1({})) return false;
                if (!validateSSN2({})) return false;
                if (!validateEmail({})) return false;
                if (!validatePhoneNumber({})) return false;

                return true;
            };

            /**
             * validateId, validatePassword 등에서 areAlphabetic, areAlphanumeric 검증이 제대로 이루어지지 않는다.
             * areNot~ 같이 negate해줘야 하거나 다른 방법을 써야 함
             */

            const validateId = ({ target = userId }) => {
                const matchesLength = /^.{4,12}$/;
                const containsDigit = /\d/;
                const areAlphabetic = /[A-Za-z]/g;
                const isLowerCase = /^[a-z]/;

                if (!matchesLength.test(userId.value)) return alert("아이디의 길이는 4~12자리여아 합니다.");
                if (!containsDigit.test(userId.value)) return alert("아이디는 숫자를 포함해야 합니다.");
                if (!areAlphabetic.test(userId.value)) return alert("아이디는 영문자와 숫자로 이루어져야 합니다.");
                if (!isLowerCase.test(userId.value)) return alert("아이디의 첫 글자는 영소문자로 시작해야 합니다.");
                return true;
            };

            const validatePassword = ({ target = pwd }) => {
                const matchesLength = /^.{8,15}$/;
                const hasValidSpecialChars = /[*!&]/;
                const areAlphanuremic = /[A-Za-z0-9]/g;

                if (!matchesLength.test(pwd.value)) return alert("비밀번호의 길이는 8~15자리여아 합니다.");
                if (!hasValidSpecialChars.test(pwd.value)) return alert("비밀번호는 특수문자(* ! &) 중 하나 이상을 포함해야 합니다.");
                if (!areAlphanuremic.test(pwd.value)) return alert( "비밀번호는 영대소문자와 숫자, 특수문자로 이루어져야 합니다.");
                return true;
            };

            const validatePasswordCheck = ({ target = pwdCheck }) => pwd.value === pwdCheck.value ? true : alert("비밀번호가 일치하지 않습니다.");

            const validateName = ({ target = userName }) => {
                const matchesLength = /^.{2,}$/;
                const areNotHanguls = /[^가-힣]/g;

                if (!matchesLength.test(userName.value)) return alert("이름은 두 글자 이상이어야 합니다.");
                if (areNotHanguls.test(userName.value)) return alert("이름은 한글로 이루어져야 합니다.");
                return true;
            };

            const validateSSN1 = ({ target = ssn1 }) => {
                const matchesLength = /^\d{6}$/;
                const isValidForm = /^\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$/;

                if (!matchesLength.test(ssn1.value)) return alert("주민번호 앞자리는 숫자 6자리입니다.");
                if (!isValidForm.test(ssn1.value)) return alert("생년월일의 형식에 맞지 않습니다.");
                return true;
            };

            const validateSSN2 = ({ target = ssn2 }) => {
                const matchesLength = /^\d{7}$/;
                const isValidForm = /^[1-4]\d{6}$/;

                if (!matchesLength.test(ssn2.value)) return alert("주민번호 뒷자리는 숫자 7자리입니다.");
                if (!isValidForm.test(ssn2.value)) return alert("주민번호 뒷자리의 형식에 맞지 않습니다.");
                return true;
            };

            const validateEmail = ({ target = email }) => {
                const matchesLength = /^[A-Za-z0-9]{4,12}/;
                const hasDomain = /(?<=@)\w+\.\w+$/;

                if (!matchesLength.test(email.value)) return alert( "이메일 주소의 아이디는\n영대소문자, 숫자로 이루어진\n4~12자리여야합니다.");
                if (!hasDomain.test(email.value)) return alert("이메일 형식에 맞지 않습니다.");
                return true;
            };

            const validatePhoneNumber = ({}) => {
                const mathcesLength = /^.{3,}$/;
                const areNotDigits = /[^\d]/g;

                if (areNotDigits.test(tel1.value) || areNotDigits.test(tel2.value) || areNotDigits.test(tel3.value)) return alert("전화번호는 숫자여야 합니다.");
                if (tel1.value !== "010") return alert("전화번호의 첫 필드는 010이어야합니다.");
                if (!mathcesLength.test(tel2.value)) return alert( "전화번호의 두 번째 필드는 3자리 이상이어야 합니다.");
                if (tel3.value.length !== 4) return alert("전화번호의 세 번째 필드는 4자리여야 합니다.");
                return true;
            };
        </script>
        ​ ​
    </body>
</html>
    