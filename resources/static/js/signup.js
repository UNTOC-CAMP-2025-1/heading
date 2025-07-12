let nicknameConfirmed = false;
let bojIdConfirmed = false;
let bojTokenVerified = false;

async function postJson(url, body) {
    const response = await fetch(url, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(body)
    });
    return response;
}

function updateSubmitButtonState() {
    const submitBtn = document.getElementById('submitBtn');
    const active = nicknameConfirmed && bojIdConfirmed && bojTokenVerified;
    submitBtn.disabled = !active;
    submitBtn.style.backgroundColor = active ? "#350FC0" : "#ccc";
    submitBtn.style.cursor = active ? "pointer" : "not-allowed";
}

async function checkNickname() {
    const nicknameInput = document.getElementById('nickname');
    const nickname = nicknameInput.value.trim();
    if (!nickname) return alert("닉네임을 입력해주세요.");
    if (nickname.length < 3 || nickname.length > 20)
        return alert("닉네임은 3자 이상 20자 이하이어야 합니다.");

    const res = await postJson('/signup/check/duplicate/nickname', { nickname });
    const isAvailable = await res.json();

    if (!isAvailable) {
        alert("이미 사용 중인 닉네임입니다.");
    } else {
        const confirmUse = confirm("사용 가능한 닉네임입니다. 사용하시겠습니까?");
        if (confirmUse) {
            nicknameInput.readOnly = true;
            nicknameInput.style.backgroundColor = "lightgray";
            nicknameConfirmed = true;
            updateSubmitButtonState();
        }
    }
}

async function checkBojId() {
    const bojIdInput = document.getElementById('id');
    const bojId = bojIdInput.value.trim();
    if (!bojId) return alert("백준 아이디를 입력해주세요.");
    if (bojId.length < 3 || bojId.length > 20)
        return alert("백준 아이디는 3자 이상 20자 이하이어야 합니다.");

    const res = await postJson('/signup/check/duplicate/bojid', { bojId });
    const isAvailable = await res.json();

    if (!isAvailable) {
        alert("이미 사용 중인 백준 아이디입니다.");
    } else {
        const confirmUse = confirm("사용 가능한 백준 아이디입니다. 사용하시겠습니까?");
        if (confirmUse) {
            bojIdInput.readOnly = true;
            bojIdInput.style.backgroundColor = "lightgray";
            bojIdConfirmed = true;
            document.getElementById("bojCheckBtn").style.display = "none";
            document.getElementById("bojTokenBtn").style.display = "inline-block";
            updateSubmitButtonState();
        }
    }
}

async function requestToken() {
    const bojId = document.getElementById('id').value.trim();
    if (!bojId) return alert("백준 아이디를 입력해주세요.");

    const res = await postJson('/signup/boj/token', { bojId });
    const token = await res.text();

    const tokenInput = document.getElementById('number');
    tokenInput.value = token;
    tokenInput.readOnly = true;
}

async function verifyToken() {
    const bojId = document.getElementById('id').value.trim();
    const token = document.getElementById('number').value.trim();
    if (!bojId || !token) return alert("백준 아이디와 인증번호를 모두 입력해주세요.");

    const res = await postJson('/signup/boj/verify', { bojId, token });
    if (res.ok) {
        alert("인증 성공!");
        bojTokenVerified = true;
        updateSubmitButtonState();
    } else {
        alert(await res.text());
    }
}

async function handleSubmit(e) {
    e.preventDefault();

    const bojId = document.getElementById('id').value.trim();
    const nickname = document.getElementById('nickname').value.trim();
    const statusMessage = document.getElementById('stat').value.trim();
    const goalTier = document.getElementById('goal').value.trim();
    const email = document.getElementById('email').value.trim();
    const grade = document.getElementById('grade').value;
    const major = document.getElementById('field').value;

    let errorMsg = "";
    if (!nickname || nickname.length < 3 || nickname.length > 20)
        errorMsg += "닉네임은 3~20자 사이여야 합니다.\n";
    if (!bojId || bojId.length < 3 || bojId.length > 20)
        errorMsg += "백준 아이디는 3~20자 사이여야 합니다.\n";
    if (!goalTier) errorMsg += "목표 티어를 선택해주세요.\n";
    if (!email) errorMsg += "이메일을 입력해주세요.\n";
    if (!grade) errorMsg += "학년을 선택해주세요.\n";
    if (!major) errorMsg += "학과를 선택해주세요.\n";

    if (errorMsg) {
        alert(errorMsg);
        return false;
    }

    const requestData = {
        bojId,
        nickname,
        statusMessage,
        goalTier,
        email,
        grade,
        major
    };

    const res = await postJson('/signup/finish', requestData);
    if (res.ok) {
        alert('회원가입 완료!');
        window.location.href = '/main';
    } else {
        alert('회원가입 실패: ' + await res.text());
    }
}
