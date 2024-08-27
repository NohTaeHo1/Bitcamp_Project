import { createSlice } from "@reduxjs/toolkit";
import { initialState } from "./officetel.init";
import {
  findAllOfficetels,
  findOfficetelById,
  findOfficetelsBoundary,
  findOfficetelsById,
  findOfficetelsByUsername,
} from "./officetel.service";

const officetelThunks = [
  findAllOfficetels,
  findOfficetelsBoundary,
  findOfficetelsById,
  findOfficetelsByUsername,
  findOfficetelById
];

const status = {
  pending: "pending",
  fulfilled: "fulfilled",
  rejected: "rejected",
};

export const officetelSlice = createSlice({
  name: "officetels",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    const { pending, rejected } = status;

    builder
      .addCase(findAllOfficetels.fulfilled, (state: any, { payload }: any) => ({
        ...state,
        JSON: payload,
      }))
      .addCase(
        findOfficetelsBoundary.fulfilled,
        (state: any, { payload }: any) => ({ ...state, array: payload })
      )
      .addCase(findOfficetelsById.fulfilled, 
        (state: any, { payload }: any) => ({
        ...state,
        Json: payload,
      }))
      .addCase(findOfficetelsByUsername.fulfilled, 
        (state: any, { payload }: any) => ({
        ...state,
        array: payload,
      }))
      .addCase(findOfficetelById.fulfilled, 
        (state: any, { payload }: any) => ({
        ...state,
        array: payload,
      }))
  },
});

export const getOfficetelJSON = (state: any) => state.officetel.JSON;
export const getOfficetelArray = (state: any) => state.officetel.array;


export const {} = officetelSlice.actions;

export default officetelSlice.reducer;
