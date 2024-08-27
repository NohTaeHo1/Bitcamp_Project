import { createAsyncThunk } from "@reduxjs/toolkit";
import { deleteByIdAPI, existsUsernameAPI, findAllUsersAPI, findUserByIdAPI, loginAPI, logoutAPI, modifyAPI } from "./user.api";
import exp from "constants";
import {IUser} from "../model/user";

export const findAllUsers: any = createAsyncThunk(
    'users/findAllUsersAPI',
    async (page:number)=>{
        const data:any = await findAllUsersAPI(page);
        const {message, result}:any = data
        return data
    }
)

export const findUserById: any = createAsyncThunk(
    'users/findUserById',
    async (id: number) => await findUserByIdAPI(id))


export const deleteById: any = createAsyncThunk(
    'users/delete',
    async (id: number) => await deleteByIdAPI(id)
)

export const modify: any = createAsyncThunk(
    'users/modify',
    async (user: any) => await modifyAPI(user)
)

export const login: any = createAsyncThunk(
    'users/login',
    async (user:IUser) => await loginAPI(user)
)

export const existsUsername: any = createAsyncThunk(
    'users/exist-Username',
    async (username:string) => await existsUsernameAPI(username)
)

export const logout: any = createAsyncThunk(
    'users/logout',
    async () => await logoutAPI()
)

