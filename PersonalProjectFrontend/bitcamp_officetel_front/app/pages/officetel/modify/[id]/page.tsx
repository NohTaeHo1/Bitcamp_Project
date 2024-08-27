"use client";

import { MyTypography } from "@/app/components/common/style/cell";
import { NextPage } from "next";
import { AttachFile, FmdGood, ThumbUpAlt } from "@mui/icons-material";
import FmdGoodIcon from "@mui/icons-material/FmdGood";
import AttachFileIcon from "@mui/icons-material/AttachFile";
import ThumbUpAltIcon from "@mui/icons-material/ThumbUpAlt";
import { PG } from "@/app/components/common/enums/PG";
import { useDispatch, useSelector } from "react-redux";
import { useEffect, useState } from "react";

import { useRouter } from "next/navigation";

import { useForm } from "react-hook-form";
import { jwtDecode } from "jwt-decode";
import { parseCookies } from "nookies";
import { OfficetelModel } from "@/app/components/officetel/model/officetel-model";
import { findOfficetelById } from "@/app/components/officetel/service/officetel.service";
import { getOfficetelArray, getOfficetelJSON } from "@/app/components/officetel/service/officetel.slice";


const WritePage: NextPage = ({params}: any) => {


  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const officetel : [] = useSelector(getOfficetelArray)


  const router = useRouter();
  const dispatch = useDispatch();

  const handelCancel = () => {
    router.push(`${PG.OFFICETEL}/list/myofficetel`);
  };

  useEffect(() => {
    console.log("1차확인 : "+params.id)
    dispatch(findOfficetelById(params.id))
  }, [])

  const onSubmit = (data: any) => {
    // alert("JSON data : " + JSON.stringify(data));
    // dispatch(save(data))
    //   .then((res: any) => {
    //     alert("글쓰기 성공");
    //     //console.log("res.payload.boardId"+JSON.stringify(res));
    //     router.push(`/pages/article/list/${data.boardId}`);
    //   })
    //   .catch((err: any) => {
    //     console.log(err);
    //     alert("error");
    //     router.push(`/pages/article/list/${data.boardId}`);
    //   });
  };

  return (
    <>
      <form onSubmit={handleSubmit(onSubmit)} className="max-w-sm mx-auto">
        <label
          htmlFor="countries"
          className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
        >
        </label>


        <div className="editor mx-auto w-10/12 flex flex-col text-gray-800 border border-gray-300 p-4 shadow-lg max-w-2xl">
          {MyTypography("해당 부동산 수정", "1.5rem")}
          <input
            type="hidden"
            value={jwtDecode<any>(parseCookies().accessToken).userId}
            readOnly
            {...register("id", { required: true })}
          />
          <input
            className="title bg-gray-100 border border-gray-300 p-2 mb-4 outline-none"
            placeholder="Title"
            type="text"
            defaultValue={params.id}
            {...register("title", { required: true })}
          />
          <textarea
            className="description bg-gray-100 sec p-3 h-60 border border-gray-300 outline-none"
            placeholder="Describe everything about this post here"
            defaultValue={params.owner}
            {...register("content", { required: true, maxLength: 300 })}
          ></textarea>
          {/* <!-- icons --> */}
          <div className="icons flex text-gray-500 m-2">
            <svg
              className="mr-2 cursor-pointer hover:text-gray-700 border rounded-full p-1 h-7"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <ThumbUpAltIcon component={ThumbUpAlt}></ThumbUpAltIcon>
            </svg>
            <svg
              className="mr-2 cursor-pointer hover:text-gray-700 border rounded-full p-1 h-7"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <FmdGoodIcon component={FmdGood}></FmdGoodIcon>
            </svg>
            <svg
              className="mr-2 cursor-pointer hover:text-gray-700 border rounded-full p-1 h-7"
              xmlns="http://www.w3.org/2000/svg"
              fill="none"
              viewBox="0 0 24 24"
              stroke="currentColor"
            >
              <AttachFileIcon component={AttachFile}></AttachFileIcon>
            </svg>
            <div className="count ml-auto text-gray-400 text-xs font-semibold">
              0/300
            </div>
          </div>
          {/* <!-- buttons --> */}
          <div className="buttons flex">
            <input type="submit" value="SUBMIT" />
            <div
              className="btn  overflow-hidden relative w-30 bg-white text-blue-500 p-3 px-4 rounded-xl font-bold uppercase -- before:block before:absolute before:h-full before:w-1/2 before:rounded-full
        before:bg-pink-400 before:top-0 before:left-1/4 before:transition-transform before:opacity-0 before:hover:opacity-100 hover:text-200 hover:before:animate-ping transition-all duration-00"
              onClick={handelCancel}
            >
              Cancel
            </div>
            <input
              type="hidden"
              value={params.id}
              {...register("id", { required: true })}
            />
          </div>
        </div>
      </form>
    </>
  );
};

export default WritePage;