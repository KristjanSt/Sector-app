import { axiosInstance } from "./axiosInstance";

export const getSectorsTree = async () => {
  const { data } = await axiosInstance.get("/sectors/tree");
  return data;
};
