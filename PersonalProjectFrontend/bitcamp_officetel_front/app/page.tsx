"use client";

import { useEffect, useRef, useState } from "react";
import Link from "next/link";
import "./globals.css";
import { useDispatch, useSelector } from "react-redux";
import { existsUsername, login } from "./components/user/service/user.service";
import {
  existsByUsername,
  getAuth,
} from "./components/user/service/user.slice";
import { useRouter } from "next/navigation";
import nookies, { parseCookies, setCookie } from "nookies";
import { jwtDecode } from "jwt-decode";
import { IUser } from "./components/user/model/user";
import { PG } from "./components/common/enums/PG";

export default function Home() {
  const dispatch = useDispatch();
  const router = useRouter();
  const auth = useSelector(getAuth);
  const [user, setUser] = useState({} as IUser);
  const [isTrueId, setisTrueId] = useState(false);
  const [isWrongId, setIsWrongId] = useState(false); // is붙이면 boolean타입 쓴다는 표식
  const [isTruePW, setisTruePW] = useState(false);
  const [isWrongPW, setIsWrongPW] = useState(false);
  const [isNotExist, setIsNotExist] = useState(false);
  const formRef = useRef<HTMLInputElement>(null);

  const existUser: boolean = useSelector(existsByUsername);

  function handleUsername(e: any) {
    const ID_CHECK = /^[A-Z]+[a-zA-Z]{5,12}$/g;
    //if(isTrueId===true){setIsNotExist(false)}

    if (e.target.value.length === 0) {
      setIsWrongId(false);
      setisTrueId(false);
    } else if (ID_CHECK.test(e.target.value)) {
      setIsWrongId(false);
      setisTrueId(true);
      setIsNotExist(false);
      setUser({ ...user, username: e.target.value });
    } else {
      setIsWrongId(true);
      setisTrueId(false);
    }
  }

  function handlePassword(e: any) {
    const PW_CHECK = /^[a-z](?=.*[!@#$%^&*]).{3,12}$/g;

    if (e.target.value.length === 0) {
      setIsWrongPW(false);
      setisTruePW(false);
    } else if (PW_CHECK.test(e.target.value)) {
      setIsWrongPW(false);
      setisTruePW(true);
      setUser({ ...user, password: e.target.value });
    } else {
      setIsWrongPW(true);
      setisTruePW(false);
    }
  }

  function handleSubmit() {

    dispatch(existsUsername(user.username))
      .then((res: any) => {
        console.log('res: ?????'+ JSON.stringify(res))
        console.log("message : " + res.payload);
        if (res.payload == true) {
          console.log("user id : "+ JSON.stringify(user))
          dispatch(login(user))
          
            .then((resp: any) => {
              console.log("서버에서 넘어온 RES " + JSON.stringify(resp));
              console.log("서버에서 넘어온 메시지 1 " + resp.payload.message);
              console.log("서버에서 넘어온 토큰 1 " + resp.payload.accessToken);
              setCookie({}, "message", resp.payload.message, {
                httpOnly: false,
                path: "/",
              });
              setCookie({}, "username", user.username ?? "", {
                httpOnly: false,
                path: "/",
              });
              setCookie({}, "accessToken", resp.payload.accessToken, {
                httpOnly: false,
                path: "/",
              });
              console.log("서버에서 넘어온 메시지 2 " + parseCookies().message);
              console.log(
                "서버에서 넘어온 토큰 2 " + parseCookies().accessToken
              );
              console.log("토큰을 디코드한 내용 : ");
              console.log(jwtDecode<any>(parseCookies().accessToken));

              router.push(`/pages/officetel/list/`);
            })
            .catch((err: any) => {
              console.log("로그인 실패");
            });
        } else {
          console.log("아이디가 존재하지 않습니다.");
          setIsNotExist(true);
          setIsWrongId(false);
          setisTrueId(false);
          setIsWrongPW(false);
          setisTruePW(false);
        }
      })
      .catch((err: any) => {})
      .finally(() => {
        console.log("최종적으로 반드시 이뤄져야 할 로직");

        if (formRef.current) {
          formRef.current.value = "";
        }
      });
  }

  // useEffect(() => {
  //   if (auth.message === "SUCCESS") {
  //     setCookie({}, "message", auth.message, { httpOnly: false, path: "/" });
  //     setCookie({}, "token", auth.token, { httpOnly: false, path: "/" });
  //     console.log("서버에서 넘어온 메시지" + parseCookies().message);
  //     console.log("서버에서 넘어온 토큰" + parseCookies().token);
      // console.log("토큰을 디코드한 내용 : ");
      // console.log(jwtDecode<any>(parseCookies().token));

  //     router.push("/pages/board/list");
  //   } else {
  //     console.log("LOGIN FAIL");
  //   }
  // }, [auth]);

  // useEffect(() => {
  //   if (existUser) {
  //     setIsNotExist(false);
  //   }else{setIsNotExist(true);}
  // }, [existUser]);

  return (
    <div className="text-center">
      <div className="text-3xl font-bold underline"></div>
      <td
              className="w-full bg-white  p-20 h-2 "
            ></td>

      <div className="flex items-center justify-center w-full h-20">
        <div
          className="text-3xl font-bold underline"
          style={{
            backgroundImage: "url('/user/img/login_logo.png')",
            backgroundRepeat: "no-repeat",
            backgroundSize: "contain",
            backgroundPosition: "center",
            width: "200px",
            height: "200px",
          }}
        ></div>
      </div>

      <br />
      <div className="flex items-center justify-center  w-full px-5 sm:px-0">
        <div className="flex bg-white rounded-lg shadow-lg border overflow-hidden max-w-sm lg:max-w-4xl w-full">
          <div
            className="hidden md:block lg:w-1/2 bg-cover bg-blue-700"
            style={{
              backgroundImage: "url('/user/img/cat.png')",
            }}
          ></div>
          <div className="w-full p-8 lg:w-1/2">
            <p className="text-xl text-gray-600 text-center">BangEZ 로그인을 해주세요</p>
            <div className="mt-4">
              <label className="block text-gray-700 text-sm font-bold mb-2 text-left">
                ID : 대문자로 시작하는 6 ~ 13 문자열 (숫자, 기호 불가)
              </label>
              <input
                onChange={handleUsername}
                className="text-gray-700 border border-gray-300 rounded py-2 px-4 block w-full focus:outline-2 focus:outline-blue-700"
                type="email"
                required
              />
            </div>
            {isWrongId && (
              <pre>
                <h6 className="text-red-600"> 사용할 수 없는 ID </h6>
              </pre>
            )}
            {isTrueId && (
              <pre>
                <h6 className="text-blue-600"> 양식에 맞는 ID </h6>
              </pre>
            )}
            {isNotExist && (
              <pre>
                <h6 className="text-red-600"> 존재하지 않는 ID </h6>
              </pre>
            )}
            <div className="mt-4 flex flex-col justify-between">
              <div className="flex justify-between">
                <label className="block text-gray-700 text-sm font-bold mb-2">
                  Password : 소문자로 시작 / 특수문자 하나 이상 포함 / 3 ~ 12 문자열
                </label>
              </div>
              <input
                onChange={handlePassword}
                className="text-gray-700 border border-gray-300 rounded py-2 px-4 block w-full focus:outline-2 focus:outline-blue-700"
                type="password"
              />
              <a
                href="#"
                className="text-xs text-gray-500 hover:text-gray-900 text-end w-full mt-2"
              >
              </a>
            </div>
            {isWrongPW && (
              <pre>
                <h6 className="text-red-600"> 사용할 수 없는 Password </h6>
              </pre>
            )}
            {isTruePW && (
              <pre>
                <h6 className="text-blue-600"> 사용할 수 있는 Password </h6>
              </pre>
            )}
            <div className="mt-8">
              <button
                onClick={handleSubmit}
                className="bg-blue-700 text-white font-bold py-2 px-4 w-full rounded hover:bg-blue-600"
              >
                로그인
              </button>
            </div>
            <a
              href={`${PG.USER}/register`}
              className=" flex items-center justify-center mt-4 text-white rounded-lg shadow-md hover:bg-gray-100"
            >
              <div className="flex px-5 justify-center w-full py-3">
                <div className="min-w-[30px]">
                  <svg className="h-6 w-6" viewBox="0 0 40 40">
                  <image href="/user/img/Logo.png" x="0" y="0" width="40" height="40" />

                  </svg>
                </div>
                <div className="flex w-full justify-center">
                  <h1 className="whitespace-nowrap text-gray-600 font-bold">
                    BangEZ 회원가입
                  </h1>
                </div>
              </div>
            </a>
            <td
              className="w-full bg-white  h-20 "
            ></td>
          </div>
        </div>
      </div>
      <br />
    </div>
  );
}
