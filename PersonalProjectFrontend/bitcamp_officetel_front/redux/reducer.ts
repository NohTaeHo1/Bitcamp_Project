import { combineReducers } from "@reduxjs/toolkit";
import { persistReducer } from "redux-persist";
import createWebStorage from "redux-persist/lib/storage/createWebStorage";
import userReducer from "@/app/components/user/service/user.slice";
import officetelReducer from "@/app/components/officetel/service/officetel.slice";

const createNoopStorage = () => {
  return {
    getItem() {
      return Promise.resolve(null);
    },
    setItem(_key: string, value: number) {
      return Promise.resolve(value);
    },
    removeItem() {
      return Promise.resolve();
    },
  };
};

const storage =
  typeof window !== "undefined"
    ? createWebStorage("local")
    : createNoopStorage();

const countPersistConfig = {
  key: "count",
  storage,
  whitelist: ["countState"],
};
const articlePersistConfig = {
  key: "article",
  storage,
  whitelist: ["articleState"],
};
const userPersistConfig = {
  key: "user",
  storage,
  whitelist: ["userState"],
};
const boardPersistConfig = {
  key: "board",
  storage,
  whitelist: ["boardState"],
};
const officetelPersistConfig = {
  key: "officetel",
  storage,
  whitelist: ["officetelState"],
};

const persistedUserReducer = persistReducer(userPersistConfig, userReducer);
const persistedOfficetelReducer = persistReducer(officetelPersistConfig, officetelReducer);

export const rootReducer = combineReducers({
  user: persistedUserReducer,
  officetel: persistedOfficetelReducer,

});
