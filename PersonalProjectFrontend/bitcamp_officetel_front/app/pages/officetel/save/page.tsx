"use client";

import { useState } from "react";
import { useRouter } from "next/navigation";
import { API } from "@/app/components/common/enums/API";
import { NextPage } from "next";
import {
  Box,
  Button,
  Container,
  TextField,
  Typography,
} from "@mui/material";
import instance from "@/app/components/common/configs/axios-config";
import { OfficetelModel } from "@/app/components/officetel/model/officetel-model";
import { parseCookies } from "nookies";
import AddressForm from "@/app/components/common/api/AddressForm";
import { jwtDecode } from "jwt-decode";

declare global {
  interface Window {
    daum: any;
  }
}

interface IAddr {
  address: string;
  zonecode: string;
}

const JoinPage: NextPage = () => {
  const [newOfficetel, setNewOfficetel] = useState<OfficetelModel>({
    user: parseCookies().username, owner: parseCookies().username
  });

  const handleOwnerType = (e: any) => {
    setNewOfficetel({ ...newOfficetel, ownerType: e.target.value });
  };
  const handleBuildingName = (e: any) => {
    setNewOfficetel({ ...newOfficetel, buildingName: e.target.value });
  };
  const handlePropertyType = (e: any) => {
    setNewOfficetel({ ...newOfficetel, propertyType: e.target.value });
  };
  const handlePrice = (e: any) => {
    setNewOfficetel({ ...newOfficetel, price: e.target.value });

  };
  const handleMonthlyRent = (e: any) => {
    setNewOfficetel({ ...newOfficetel, monthlyRent: e.target.value });
  };
  const handleArea = (e: any) => {
    setNewOfficetel({ ...newOfficetel, area: e.target.value });
  };
  const handleFloor = (e: any) => {
    setNewOfficetel({ ...newOfficetel, floor: e.target.value });
  };
  const handleDirection = (e: any) => {
    setNewOfficetel({ ...newOfficetel, direction: e.target.value });
  };

  const router = useRouter();

  const handleCancel = () => {};

  const handleSignup = (e: any) => {
    e.preventDefault();

    const url = `${API.SERVER}/officetel/insert`;

    instance()
      .post(url, newOfficetel)
      .then((res) => {
        alert("매물등록 : " + JSON.stringify(res.data.message));
        router.push(`/pages/officetel/list`);
      });
  };

  const [zipCode, setZipCode] = useState('');
  const [address, setAddress] = useState('');
  const [addressDetail, setAddressDetail] = useState('');

  const openZipSearch = () => {
    new window.daum.Postcode({
      oncomplete: function (data: any) {
        let addr = '';
        if (data.userSelectedType === 'R') {
          addr = data.roadAddress;
        } else {
          addr = data.jibunAddress;
        }

        setZipCode(data.zonecode);
        setAddress(addr);
        setAddressDetail('');
        (document.getElementById('addr_dtl') as HTMLInputElement).focus();
      },
    }).open();
  };

  return (
    <Container maxWidth="sm">
      <Typography variant="h4" component="h1" gutterBottom>
        매물등록
      </Typography>
      <Typography variant="body1" gutterBottom>
        아래 항목을 채워주세요.
      </Typography>
      <Box component="form" noValidate autoComplete="off">
        <TextField
          fullWidth
          label="중개사는 1, 개인은 2 를 기입해주세요."
          name="ownerType"
          type="ownerType"
          required
          margin="normal"
          onChange={handleOwnerType}
        />
        <TextField
          fullWidth
          label="건물이름"
          name="psw"
          required
          type="buildingName"
          margin="normal"
          onChange={handleBuildingName}
        />

        <AddressForm/>

        <TextField
          fullWidth
          label="매매는 1, 월세는 2, 전세는 3을 기입해 주세요."
          name="psw-repeat"
          required
          type="propertyType"
          margin="normal"
          onChange={handlePropertyType}
        />
        <TextField
          fullWidth
          label="가격"
          margin="normal"
          onChange={handlePrice}
        />
        <TextField
          fullWidth
          label="월세(월세인 경우만)"
          margin="normal"
          onChange={handleMonthlyRent}
        />
        <TextField
          fullWidth
          label="면적"
          margin="normal"
          onChange={handleArea}
        />
        <TextField
          fullWidth
          label="층수"
          margin="normal"
          onChange={handleFloor}
        />
                <TextField
          fullWidth
          label="방향"
          margin="normal"
          onChange={handleDirection}
        />
        <Box mt={2}>
          <Button variant="outlined" color="secondary" onClick={handleCancel}>
            취소
          </Button>{" "}
          <Button
            variant="contained"
            color="primary"
            onClick={handleSignup}
            type="submit"
          >
            등록
          </Button>
        </Box>
      </Box>
    </Container>
  );
};

export default JoinPage;
