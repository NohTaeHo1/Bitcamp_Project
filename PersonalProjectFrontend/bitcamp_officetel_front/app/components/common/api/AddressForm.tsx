// src/components/AddressForm.tsx

import { Button } from "@mui/material";
import React, { useState, useEffect } from "react";

const AddressForm: React.FC = () => {
  const [zipCode, setZipCode] = useState<string>("");
  const [address, setAddress] = useState<string>("");
  const [addressDetail, setAddressDetail] = useState<string>("");

  useEffect(() => {
    // Daum 우편번호 서비스 스크립트 로드
    const script = document.createElement("script");
    script.src =
      "https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
    script.async = true;
    document.body.appendChild(script);

    // script.onload = () => {
    //   initializeDaumPostcode();
    // };

    return () => {
      document.body.removeChild(script);
    };
  }, []);

  const initializeDaumPostcode = () => {
    new window.daum.Postcode({
      oncomplete: function (data: any) {
        let fullAddress = data.address;
        let extraAddress = "";

        if (data.addressType === "R") {
          if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
            extraAddress += data.bname;
          }
          if (data.buildingName !== "" && data.apartment === "Y") {
            extraAddress +=
              extraAddress !== ""
                ? `, ${data.buildingName}`
                : data.buildingName;
          }
          fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
        }

        setZipCode(data.zonecode);
        setAddress(fullAddress);
        setAddressDetail("");
        document.getElementById("addr_dtl")?.focus();
      },
    }).open();
  };

  const handleAddressDetailChange = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setAddressDetail(e.target.value);
  };

  return (
    <div>
      <tr>
        <Button variant="contained" size="small"  onClick={initializeDaumPostcode}>

            주소 검색
        </Button>
      </tr>
      <label>우편번호</label>
      <input
        type="text"
        id="zip_code"
        name="zip_code"
        readOnly
        value={zipCode}
        placeholder="우편번호"
        style={{ width: "250px" }}
      />
      <br />
      <label>기본주소</label>
      <input
        type="text"
        id="addr"
        name="addr"
        readOnly
        value={address}
        placeholder="기본주소"
        style={{ width: "400px" }}
      />
      <br />
      <label>상세주소</label>
      <input
        type="text"
        id="addr_dtl"
        name="addr_dtl"
        value={addressDetail}
        onChange={handleAddressDetailChange}
        placeholder="상세주소를 입력하세요"
        style={{ width: "400px" }}
      />
    </div>
  );
};

export default AddressForm;
