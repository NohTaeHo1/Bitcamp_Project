"use client";

import { IUser } from "@/app/components/user/model/user";
import UserColumns from "@/app/components/user/module/columns";
import { findAllUsers } from "@/app/components/user/service/user.service";
import { getAllUsers } from "@/app/components/user/service/user.slice";
import { Box } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { jwtDecode } from "jwt-decode";
import { NextPage } from "next";
import { parseCookies } from "nookies";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";

const UsersPage: NextPage = () => {
  console.log("토큰을 디코드한 내용 : ");
  console.log(jwtDecode<any>(parseCookies().token));
  const dispatch = useDispatch();
  const allUsers: [] = useSelector(getAllUsers);

  useEffect(() => {
    dispatch(findAllUsers(1));
  }, []);

  return (
    <>
      <Box sx={{ height: 400, width: "100%" }}>
        {allUsers && (
          <DataGrid
            rows={allUsers}
            columns={UserColumns()}
            initialState={{
              pagination: {
                paginationModel: {
                  pageSize: 5,
                },
              },
            }}
            pageSizeOptions={[5]}
            checkboxSelection
            disableRowSelectionOnClick
          />
        )}
      </Box>
    </>
  );
}

export default UsersPage;
