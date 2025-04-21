import { axiosInstance } from "./axiosInstance";

export const saveUserData = async (data) => {
  const response = await axiosInstance.post("/user-data/save", data);
  return response.data;
};
