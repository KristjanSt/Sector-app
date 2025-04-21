import { Box, Button, Stack, Typography } from "@mui/material";
import { useForm } from "@tanstack/react-form";
import React, { useState } from "react";

import NameField from "./NameField";
import SectorsField from "./SectorsField";
import AgreementField from "./AgreementField";
import LoadingOrErrorState from "./LoadingOrErrorState";
import { useMutation, useQuery } from "@tanstack/react-query";
import { getSectorsTree } from "../../api/sectors";
import { saveUserData } from "../../api/userData";
import Toast from "../common/Toast";
import { useTranslation } from "react-i18next";

function SectorForm() {
  const { t } = useTranslation();
  const {
    data: sectorsData = [],
    isLoading,
    isError,
  } = useQuery({
    queryKey: ["sectors"],
    queryFn: getSectorsTree,
  });

  const [toast, setToast] = useState({
    open: false,
    message: "",
    severity: "success",
  });

  const mutation = useMutation({
    mutationFn: saveUserData,
    onSuccess: () => {
      setToast({
        open: true,
        message: "Data submitted successfully!",
        severity: "success",
      });
      form.reset();
    },
    onError: (error) => {
      setToast({
        open: true,
        message: error.response?.data?.message || "Submission failed.",
        severity: "error",
      });
    },
  });

  const form = useForm({
    defaultValues: {
      name: "",
      sectors: [],
      agreedToTerms: false,
    },
    onSubmit: async ({ value }) => {
      mutation.mutate(value);
    },
  });

  const idToNameMap = React.useMemo(() => {
    const map = {};
    const traverse = (nodes) => {
      for (const node of nodes) {
        map[node.id] = node.name;
        if (node.children) traverse(node.children);
      }
    };
    traverse(sectorsData);
    return map;
  }, [sectorsData]);

  if (isLoading || isError)
    return <LoadingOrErrorState isLoading={isLoading} isError={isError} />;

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        minHeight: "100vh",
        width: "100%",
        paddingTop: "5%",
        bgcolor: "secondary.main",
      }}
    >
      <form
        onSubmit={(e) => {
          e.preventDefault();
          e.stopPropagation();
          form.handleSubmit();
        }}
      >
        <Stack spacing={2}>
          <Typography variant="h5" align="center" color="primary.contrastText">
            {t("form.title")}
          </Typography>

          <NameField form={form} />
          <SectorsField
            form={form}
            sectorsData={sectorsData}
            idToNameMap={idToNameMap}
          />
          <Box sx={{ display: "flex", justifyContent: "space-between" }}>
            <AgreementField form={form} />
            <form.Subscribe
              selector={(state) => [state.canSubmit, state.isSubmitting]}
              children={([canSubmit, isSubmitting]) => (
                <Button
                  type="submit"
                  disabled={!canSubmit}
                  color="primary"
                  variant="contained"
                >
                  {isSubmitting
                    ? t("form.submittingInProgress")
                    : t("form.submit")}
                </Button>
              )}
            />
          </Box>
        </Stack>
      </form>
      <Toast
        open={toast.open}
        onClose={() => setToast({ ...toast, open: false })}
        severity={toast.severity}
        message={toast.message}
      />
    </Box>
  );
}

export default SectorForm;
