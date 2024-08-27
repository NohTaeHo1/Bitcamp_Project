"use client";
import axios from "axios";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { API } from "@/app/components/common/enums/API";
import AxiosConfig from "@/app/components/common/configs/axios-config";
import { PG } from "@/app/components/common/enums/PG";
import { NextPage } from "next";
import {
  Box,
  Button,
  Checkbox,
  Container,
  FormControlLabel,
  Link,
  TextField,
  Typography,
} from "@mui/material";
import instance from "@/app/components/common/configs/axios-config";
const SERVER = "http://localhost:8081";

const JoinPage: NextPage = () => {
  const [username, setusername] = useState("");
  const [password, setpassword] = useState("");
  const [passwordConfirm, setpasswordConfirm] = useState("");
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [job, setjob] = useState("");
  const [height, setheight] = useState("");
  const [weight, setweight] = useState("");

  const handleUserName = (e: any) => {
    setusername(e.target.value);
  };
  const handlePassword = (e: any) => {
    setpassword(e.target.value);
  };
  const handlePasswordConfirm = (e: any) => {
    setpasswordConfirm(e.target.value);
  };
  const handleName = (e: any) => {
    setName(e.target.value);
  };
  const handlePhone = (e: any) => {
    setPhone(e.target.value);
  };
  const handleJop = (e: any) => {
    setjob(e.target.value);
  };

  const router = useRouter();

  const handleCancel = () => {};

  const handleSignup = (e: any) => {
    e.preventDefault();

    console.log(username + "님 회원가입 성공");

    const url = `${API.SERVER}/users/save`;
    const data = {
      username,
      password,
      passwordConfirm,
      name,
      phone,
      job,
    };
    instance().post(url, data).then((res) => {
      alert("회원가입 : " + JSON.stringify(res.data.message));
      router.push(`../../`);
    });
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" component="h1" gutterBottom>
        회원가입
      </Typography>
      <Typography variant="body1" gutterBottom>
        아래 항목을 채워주세요.
      </Typography>
      <Box component="form" noValidate autoComplete="off">
        <TextField
          fullWidth
          label="아이디(대문자로 시작하는 6 ~ 13 문자열 (숫자, 기호 불가)"
          name="username"
          required
          margin="normal"
          onChange={handleUserName}
        />
        <TextField
          fullWidth
          label="비밀번호(소문자 시작 / 특수문자 하나 이상 / 3 ~ 12 문자열)"
          name="psw"
          required
          type="password"
          margin="normal"
          onChange={handlePassword}
        />
        <TextField
          fullWidth
          label="비밀번호 확인"
          name="psw-repeat"
          required
          type="password"
          margin="normal"
          onChange={handlePasswordConfirm}
        />
        <TextField
          fullWidth
          label="이름"
          margin="normal"
          onChange={handleName}
        />
        <TextField
          fullWidth
          label="전화 번호"
          margin="normal"
          onChange={handlePhone}
        />
        <TextField fullWidth label="직업" margin="normal" onChange={handleJop} />

        <Box mt={2}>
          <Button variant="outlined" color="secondary" onClick={handleCancel}>
            취소
          </Button>{" "}
          <Button
            variant="contained"
            color="primary"
            onClick={handleSignup}
            type="submit"
          >
            회원가입
          </Button>
        </Box>
      </Box>
    </Container>
  );
};

export default JoinPage;
