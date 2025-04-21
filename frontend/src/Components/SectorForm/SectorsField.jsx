import {
  Box,
  FormControl,
  FormHelperText,
  InputLabel,
  MenuItem,
  OutlinedInput,
  Select,
  Typography,
} from "@mui/material";
import { useTranslation } from "react-i18next";

const renderSectorOptions = (sectors, level = 0) =>
  sectors.flatMap((sector) => {
    const currentOption = (
      <MenuItem
        key={sector.id}
        value={sector.id}
        sx={{ paddingLeft: level * 2 }}
      >
        {sector.name}
      </MenuItem>
    );
    const childrenOptions = sector.children?.length
      ? renderSectorOptions(sector.children, level + 2)
      : [];

    return [currentOption, ...childrenOptions];
  });

// eslint-disable-next-line no-unused-vars
export default function SectorsField({ form, sectorsData, idToNameMap }) {
  const { t } = useTranslation();

  return (
    <form.Field
      name="sectors"
      validators={{
        onBlur: ({ value }) => {
          if (value.length === 0) return t("form.sectors.required");
        },
      }}
      children={({ state, handleChange, handleBlur }) => (
        <FormControl fullWidth error={state.meta.errors.length > 0}>
          <InputLabel id="sectors-label" color="primary">
            {t("form.sectors.label")}
          </InputLabel>
          <Select
            labelId="sectors-label"
            id="sectors"
            multiple
            value={state.value}
            onChange={(e) => handleChange(e.target.value)}
            onBlur={handleBlur}
            input={<OutlinedInput label={t("form.sectors.label")} />}
            renderValue={(selected) => (
              <Box sx={{ display: "flex", flexWrap: "wrap", gap: 0.5 }}>
                {selected.map((value) => (
                  <Typography key={value}>
                    {idToNameMap[value] || t("form.sectors.unkwnown")}
                  </Typography>
                ))}
              </Box>
            )}
          >
            {renderSectorOptions(sectorsData)}
          </Select>
          <FormHelperText>{state.meta.errors[0] || ""}</FormHelperText>
        </FormControl>
      )}
    />
  );
}
