import { createSlice } from "@reduxjs/toolkit";
import { existsUsername, findAllUsers, findUserById, login } from "./user.service";
import {IUser} from "../model/user";

const userThunks = [findAllUsers, findUserById, login, existsUsername];

const status = {
  pending: "pending",
  fulfilled: "fulfilled",
  rejected: "rejected",
};

interface IAuth{
  message?:string,
  token?:string
}

interface UserState {
  array?:Array<IUser>,
  json?:IUser,
  auth?:IAuth,
  boolean?:boolean
}

export const initialState:UserState = {
  json: {} as IUser,
  array:[],
  auth: {} as IAuth,
}

const handleFulfilled = (state: any, { payload }: any) => {
  state.array = payload;
};

const handlePending = (state: any) => {};
const handleRejected = (state: any) => {};

export const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {}, //내부 연산
  extraReducers: (builder) => {
    //자바연동
    const { pending, rejected } = status; //진행중, 거부
    builder
      .addCase(findAllUsers.fulfilled, handleFulfilled)
      .addCase(findUserById.fulfilled, (state: any, { payload }: any) => {
        state.array = payload;
      })
      .addCase(login.fulfilled, (state: any, { payload }: any) => {
        state.auth = payload;
      })
      .addCase(existsUsername.fulfilled, (state: any,  {payload} : any) => {
        state.boolean = payload;
      });
  },
});

export const getAllUsers = (state: any) => state.user.array;
export const getUserById = (state: any) => state.user.json;
export const getAuth = (state: any) => state.user.auth;
export const existsByUsername = (state:any) => {
  return state.user.boolean};

export const {} = userSlice.actions;

export default userSlice.reducer;
