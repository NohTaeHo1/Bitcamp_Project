import { createAsyncThunk } from "@reduxjs/toolkit";
import {
  deleteOfficetelsByIdAPI,
  findAllOfficetelsAPI,
  findOfficetelByIdAPI,
  findOfficetelsBoundaryAPI,
  findOfficetelsByIdAPI,
  findOfficetelsByUsernameAPI,
  modifyOfficetelByIdAPI,
} from "./officetel.api";
import { OfficetelSearch } from "../model/officetel-search";
import { OfficetelModel } from "../model/officetel-model";

export const findAllOfficetels: any = createAsyncThunk(
  "officetel/findAllOfficetelsAPI",
  async (page: number) => {
    const data: any = await findAllOfficetelsAPI(1, 10);
    const { message, result }: any = data;
    return data;
  }
);// 카드 페이지에 넣을거

export const findOfficetelsBoundary: any = createAsyncThunk(
  "officetel/findOfficetelsBoundaryAPI",
  async (searchInfo:OfficetelSearch) => {
    const data: any = await findOfficetelsBoundaryAPI(searchInfo);
    const { message, result }: any = data;

    return data;
  }
); // 디테일 페이지에 체크박스로 넣을거

export const findOfficetelsById: any = createAsyncThunk(
  "officetel/findOfficetelsByIdAPI",
  async (id: number) => {
    const data: any = await findOfficetelsByIdAPI(id);
    const { message, result }: any = data;
    return data;
  }
);

export const deleteOfficetelsById: any = createAsyncThunk(
  "officetel/deleteOfficetelsByIdAPI",
  async (id: number) => {
    const data: any = await deleteOfficetelsByIdAPI(id);
    const { message, result }: any = data;
    return data;
  }
);

export const modifyOfficetelById: any = createAsyncThunk(
  "officetel/deleteOfficetelsByIdAPI",
  async (row: OfficetelModel) => {
    const data: any = await modifyOfficetelByIdAPI(row);
    const { message, result }: any = data;
    return data;
  }
);

export const findOfficetelsByUsername: any = createAsyncThunk(
  "officetel/findOfficetelsByUsernameAPI",
  async () => {
    const data: any = await findOfficetelsByUsernameAPI();
    const { message, result }: any = data;

    return data;
  }
);

export const findOfficetelById: any = createAsyncThunk(
  "officetel/findOfficetelByIdAPI",
  async (id: number) => {
    const data: any = await findOfficetelByIdAPI(id);
    const { message, result }: any = data;
    console.log("서버에서 넘어오는 데이터 확인 : "+JSON.stringify(data))

    return data;
  }
);