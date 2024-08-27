import instance from "@/app/components/common/configs/axios-config";
import { OfficetelModel } from "../model/officetel-model";
import { OfficetelSearch } from "../model/officetel-search";
import qs from "qs";
import { parseCookies } from "nookies";

export const findAllOfficetelsAPI = async (page: number, size: number) => {
  try {
    const response = await instance().get(`/officetel/search/`, {
      params: { q: "3" },
    });
    return response.data.list;
  } catch (error) {
    return error;
  }
};

export const findOfficetelsBoundaryAPI = async (
  searchInfo: OfficetelSearch
) => {
  const queryString = qs.stringify({
    q: "2",
    oTvalue: searchInfo.OTValues,
    pTvalue: searchInfo.PTValues,
    cost: searchInfo.cost,
  }, { arrayFormat: 'repeat' });

  const url = `/officetel/search?${queryString}`;
  try {
    const response = await instance().get(url);

    return response.data.list;
  } catch (error) {
    return error;
  }
};

export const findOfficetelsByIdAPI = async (id: number) => {
  try {
    const response = await instance().get(`/officetel/search/${id}`, {
      params: { q: "4", id: id },
    });
    return response.data.list;
  } catch (error) {
    return error;
  }
}; // 이거 전체가 axios

export const deleteOfficetelsByIdAPI = async (id: number) => {
  try {
    const response = await instance().delete(`/officetel/remove/${id}`);
    return response.data.list;
  } catch (error) {
    return error;
  }
}; // 이거 전체가 axios

export const modifyOfficetelByIdAPI = async (row: OfficetelModel) => {
  try {
    const response = await instance().post(`/officetel/modify`, {
      params: { row },
    });
    return response.data.list;
  } catch (error) {
    return error;
  }
};


export const findOfficetelsByUsernameAPI = async () => {
  const user = parseCookies().username
  try {
    const response = await instance().get(`/officetel/search`, {params: {q: "5", user: user, page: 1, size: 10}});
    return response.data.list;
  } catch (error) {
    return error;
  }
};

export const findOfficetelByIdAPI = async (id : number) => {
  try {
    const response = await instance().get(`/officetel/search`, {params: {q: "4", id: id, page: 1, size: 10}});

    return response.data.list;
  } catch (error) {
    return error;
  }
};
